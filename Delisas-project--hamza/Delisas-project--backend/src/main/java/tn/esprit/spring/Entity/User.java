package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class User implements Serializable{ 
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private int idUser;
	
    private String userName;

	private String password;

	private String mail;
	
	private String cin;
	
	private String tel;

}
