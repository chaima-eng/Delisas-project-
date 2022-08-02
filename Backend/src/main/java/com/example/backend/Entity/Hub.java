package com.example.backend.Entity;

import lombok.*;

import javax.persistence.*;

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
