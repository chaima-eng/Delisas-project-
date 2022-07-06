package com.example.backend.Entity;

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




}
