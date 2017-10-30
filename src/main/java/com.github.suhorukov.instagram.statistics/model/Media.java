package com.github.suhorukov.instagram.statistics.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Media {
    @Id
    public Long id;
    public long createdTime;
    public String type;
    @Column(name = "caption", length = 2048)
    public String caption;
    public String shortcode;
    public int commentsCount;
    public int likesCount;
    public int videoViews;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account owner;
    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;
    private String imageLink;
    private String videoLink;
    @OneToMany(mappedBy = "media")
    public List<Comment> comments = new ArrayList<>();
    @ManyToMany
    public List<Account> likes = new ArrayList<>();
}
