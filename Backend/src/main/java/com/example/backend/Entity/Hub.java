package com.example.backend.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Hub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idhub;
}
