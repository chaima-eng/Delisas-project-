package com.example.backend.Controller;

import javax.servlet.http.Cookie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Roles;
import com.example.backend.Entity.User;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Payload.LoginRequest;
import com.example.backend.Repository.IntPersonnelRepo;
import com.example.backend.Service.IntPersonnelService;
import com.example.backend.Service.SendMail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/Rest")
@Slf4j
@CrossOrigin("*")
public class RestController {


    @Autowired
    IntPersonnelService Myservice;



    @Autowired
    private IntPersonnelRepo MyPersonnelRepo;
    @Autowired
    ServletContext context;












    @PutMapping("/personnel/{id}")
    public ResponseEntity<Personnel> updatePerso(@PathVariable("id") int id,@RequestBody Personnel personnel)
    {
        return Myservice.updatePerso(id,personnel);
    }






    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null&& authorizationHeader.startsWith("bearer "))
        {
            try
            {       String refresh_token = authorizationHeader.substring("bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT= verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Personnel user = Myservice.getPersonnel(username);

                String access_token = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens=new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }
            catch (Exception exception)
            {
                log.error("Error logging in {} ",exception.getMessage());
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String,String> error=new HashMap<>();
                error.put("error_msg",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);

            }
        }
        else{
            throw new RuntimeException("refresh token is missing ");
        }


    }

    @PostMapping("personnel/roles/{username}/{rolename}")
    void addRoleToUser(@PathVariable(value = "username")String username,@PathVariable(value = "rolename") String rolename)
    {
        Myservice.addRoleToUser(username,rolename);

    }
    @PostMapping("personnel/roles")
    public Roles save(@RequestBody Roles role)
    {
        return Myservice.save(role);
    }

    @PostMapping("/personnel")
    public ResponseEntity<Response>  addPersonnel (@RequestPart("file") MultipartFile file,@RequestPart("file2") MultipartFile file2,@RequestPart("file3") MultipartFile file3,
                                                   @RequestParam("personnel") String personnel) throws JsonParseException, JsonMappingException, Exception
    {

        return Myservice.addPersonnel(file,file2,file3,personnel);
    }



    @GetMapping("/personnels/{id}")
    public ResponseEntity<Personnel> getPersoById(@PathVariable(value = "id") int Id)
            throws ResourceNotFoundException
    {
        return Myservice.getPersoById(Id);
    }

    @PutMapping("/personnel")
    public Personnel updatePerso( @RequestBody Personnel personnel) throws ResourceNotFoundException {
        return Myservice.updatePerso(personnel);
    }

    @DeleteMapping("/personnel/{id}")
    public Map<String, Boolean> deletePerso(@PathVariable(value = "id") int PersoId)
            throws ResourceNotFoundException

    {
        return Myservice.deletePerso(PersoId);
    }


    @GetMapping("/personnels")
    List<Personnel> getAllPersonnel()
    {
        return Myservice.getAllPersonnel();
    }


    @GetMapping(path="/Imgarticles/{id}")
    public byte[] getPhoto(@PathVariable("id") int id) throws Exception{
        Personnel personnel   = MyPersonnelRepo.findById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+personnel.getPhoto()+personnel.getCartegrise()+personnel.getPermis()));
    }






    @Autowired
    private SendMail email;

    @PutMapping("/traiter-mail/{iduser}")
    @ResponseBody
    public void traitermail(@PathVariable("iduser")int iduser) {
        email.traiterMail(iduser);
    }



    @PutMapping("/mail-perso/{iduser}")
    @ResponseBody
    public void MailPers(@PathVariable("iduser")int iduser) {
        email.MailPerso(iduser);
    }









}
