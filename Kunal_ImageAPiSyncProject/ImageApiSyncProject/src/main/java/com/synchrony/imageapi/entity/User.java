package com.synchrony.imageapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "my_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String password;

    private String mobilenumber;


}
