package com.github.suhorukov.instagram.statistics.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Comment {
    @Id
    private Long id;
    private String text;
    private Long createdAt;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Account user;
}
