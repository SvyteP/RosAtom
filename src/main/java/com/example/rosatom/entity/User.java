package com.example.rosatom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    @Column(name = "name")
    private String name;
    @OneToMany( cascade = CascadeType.ALL,mappedBy = "users")
    private ArrayList<Massage> massages;

}
