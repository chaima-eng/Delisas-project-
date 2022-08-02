package com.example.backend.Service;

import com.example.backend.Entity.Fournisseur;
import com.example.backend.Entity.Personnel;
import com.example.backend.Entity.User;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntFournisseurRepo;
import com.example.backend.Repository.IntPersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SendMail {

    @Autowired
    EmailSenderService emailService;
    @Autowired
    IntFournisseurRepo frepo;
    @Autowired
    IntPersonnelRepo Prepo;

    public void traiterMail( int iduser) {
        // TODO Auto-generated method stub
        Fournisseur f = frepo.findById(iduser).orElse(null);
        emailService.sendSimpleEmail(f.getEmail(), "creation du compte  ", "votre compte est cree avec un usename "+f.getEmail()+
                "et un mot de passe "+f.getTel(),false);


    }

    public void MailPerso(int idUser)
    {
        Personnel P = Prepo.findById(idUser).orElse(null);
        emailService.sendSimpleEmail(P.getEmail(),"creation du compte  ", "votre compte est cree avec un usename "+P.getEmail()+
                "et un mot de passe "+P.getTel(),false);
    }








}
