package com.example.rosatom.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "topic")
    private ArrayList<Massage> topic_mass;
}
