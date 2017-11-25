package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.CarouselResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselResourceRepository extends JpaRepository<CarouselResource, String> {
}
