package com.example.rosatom.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import java.util.ArrayList;
import java.util.Date;
@Data
@Entity
@Table(name = "massege_table")
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "body_mass")
    private String body;
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "users")
    private User users;

    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;

    public Massage() {
    }

}
