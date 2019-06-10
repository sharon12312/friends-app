package com.wiredbrain.friends.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String city;

    @ManyToOne
    private Friend friend;
}
