package com.wiredbrain.friends.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @JsonProperty("first-name")
    private String firstName;
    @JsonProperty("last-name")
    private String lastName;
    private int age;
    @JsonIgnore
    boolean married;
    @OneToMany(mappedBy = "friend")
    List<Address> addresses;

    public Friend(@NotBlank String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Friend() {}
}
