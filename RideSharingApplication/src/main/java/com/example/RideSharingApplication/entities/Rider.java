package com.example.RideSharingApplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rider {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private double rating;

    //rider will have one to one mapping with the user.
    //each rider will have one user
    @OneToOne
    @JoinColumn(name = "user_id") //rider table will have this column which will be the FK which is PK of User
    private User user;

}
