package com.github.suhorukov.instagram.statistics.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Account {
    @Id
    public Long id;
    @Column(name = "username", nullable = false)
    public String username;
    public int followsCount;
    public int followedByCount;
    public String profilePicUrl;
    @Column(name = "biography", length = 2048)
    public String biography;
    public String fullName;
    public int mediaCount;
    public boolean privateAccount;
    public String externalUrl;
    public boolean verified;
    public Date fullDataUpdated;
}
