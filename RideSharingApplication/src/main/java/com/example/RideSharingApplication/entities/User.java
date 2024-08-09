package com.example.RideSharingApplication.entities;

import com.example.RideSharingApplication.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
//    used to define a collection of basic or embeddable types.
//    In this case, it's used to indicate that the roles field is a collection of elements (in this case, Role enum values)
//    that are not entities themselves but are embedded within the User entity
//This specifies the fetching strategy for the collection.
// With FetchType.LAZY, the collection is fetched lazily, meaning that it will not be loaded from the database until it is
// accessed for the first time. This can improve performance by avoiding unnecessary database
// queries when the collection is not needed.
    @Enumerated(EnumType.STRING)
//    This annotation specifies that the elements of the collection are enumerated types
//This indicates that the enum values should be persisted as their corresponding string names in the database,
// rather than their ordinal (integer) values.
    private Set<Role> roles;
}
