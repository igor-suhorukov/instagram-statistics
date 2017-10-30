package com.github.suhorukov.instagram.statistics.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text", length = 2048)
    private String text;
    private Long createdAt;
    @OneToOne
    private Media media;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account user;
}
