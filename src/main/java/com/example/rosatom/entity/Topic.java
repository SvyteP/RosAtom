package com.example.rosatom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "topic")
    private List<Massage> topic_mass = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "users")
    private User users;

    public Topic() {
    }

    public Topic(String title, User users) {
        this.title = title;
        this.users = users;
    }
}
