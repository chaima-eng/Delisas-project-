package tn.esprit.spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUser;
    private String userName;
    @JsonIgnore
    private String password;
    private String cin;
    private String tel;
    private String email;

private String lastName;
    private String code;








}
