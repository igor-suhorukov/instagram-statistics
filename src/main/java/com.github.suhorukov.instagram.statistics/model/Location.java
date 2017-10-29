package com.github.suhorukov.instagram.statistics.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Location {
    @Id
    private Long id;
    private String name;
    private String slug;
    private boolean hasPublicPage;
    private Double lat;
    private Double lng;
}
