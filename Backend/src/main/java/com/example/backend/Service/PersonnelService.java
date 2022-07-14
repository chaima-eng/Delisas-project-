package com.example.backend.Service;

import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.Role;
import com.example.backend.Entity.Roles;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntPersonnelRepo;
import com.example.backend.Repository.IntRolesRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.xml.ws.Response;
import java.io.File;
import java.util.*;


@Service
@Slf4j
public class PersonnelService implements IntPersonnelService, UserDetailsService
{
    @Autowired
    private IntPersonnelRepo MyPersonnelRepo;
    @Autowired
    ServletContext context;

    @Autowired
    private IntRolesRepo MyRolesRepo;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




/*
    @Override
    public void LoginAdmin(String password, String username) {
        Personnel perso = new Personnel();
        if(username.equals("admin")&&password.equals("admin")) {
            System.out.println("Welcome");

            perso.setPassword(password);
            perso.setUserName(username);
            MyPersonnelRepo.save(perso);
        }
    }






 */


    @Override
    public ResponseEntity<Response> addPersonnel(MultipartFile file, String personnel,int idroles) throws JsonParseException, JsonMappingException, Exception
    {

        System.out.println("Ok .............");
        Personnel perso = new ObjectMapper().readValue(personnel, Personnel.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mkdir success.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }




        String pass = perso.getPassword();
        String DefaultPasword = perso.getTel();

        perso.setPassword(passwordEncoder.encode(DefaultPasword));
        perso.setPhoto(newFileName);

        Roles roles= MyRolesRepo.findById( idroles).orElse(null);
        roles.setName(roles.getName());
        //roles.getPersonnels().add(perso);
        perso.getRoles().add(roles);

        Personnel art = MyPersonnelRepo.save(perso);



        if (art != null)
        {
            return new ResponseEntity<Response>( HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }



    }


    @Override
    public ResponseEntity<Personnel> getPersoById( int Id)
            throws ResourceNotFoundException {
        Personnel personnel = MyPersonnelRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("personnel not found for this id :: " + Id));
        return ResponseEntity.ok().body(personnel);
    }


   @Override
    public ResponseEntity<Personnel> updatePerso(int id, Personnel personnel) {
        System.out.println("Update Personnel with ID = " + id + "...");
        Optional<Personnel> Personnelinfo = MyPersonnelRepo.findById(id);
        if (Personnelinfo.isPresent()) {
            Personnel perso = Personnelinfo.get();
            perso.setCartegrise(personnel.getCartegrise());
            perso.setMatriculevehicule(personnel.getMatriculevehicule());
            perso.setPermis(personnel.getPermis());
            perso.setMontant_l(personnel.getMontant_l());
            perso.setSalaire(personnel.getSalaire());
            perso.setRoles(personnel.getRoles());

            return new ResponseEntity<>(MyPersonnelRepo.save(personnel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Map<String, Boolean> deletePerso(int PersoId) throws ResourceNotFoundException {
        Personnel Personnel = MyPersonnelRepo.findById(PersoId)
                .orElseThrow(() -> new ResourceNotFoundException(" not found   :: " + PersoId));
        MyPersonnelRepo.delete(Personnel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }




    @Override
    public List<Personnel> getAllPersonnel() {
        return (List<Personnel>) MyPersonnelRepo.findAll();
    }


    @Override
    public Roles save(Roles role) {
        return MyRolesRepo.save(role);
    }

    @Override
    public Personnel getPersonnel(String username) {
        return MyPersonnelRepo.findByUserName(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        Personnel appUser=MyPersonnelRepo.findByUserName(username);
        Roles appRole=MyRolesRepo.findByName(rolename);
        appUser.getRoles().add(appRole);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personnel user = MyPersonnelRepo.findByUserName(username);
        if(user==null)
        {
            log.error("user not found in the DB");
            throw new UsernameNotFoundException("user not found in the DB");
        }
        else
        {
            log.info("user is here",username);

        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles ->{
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        } );

        return new
                org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }


















 /*
    @Override
    public void LoginPersonnel(String password, String username) {

        MyPersonnelRepo.findAll().forEach(P -> {
            if (P.getPassword().equals(password) && P.getUserName().equals(username))

                System.out.println("done");

            else

                System.out.println("erreur");


        } );




    }


     */







}
