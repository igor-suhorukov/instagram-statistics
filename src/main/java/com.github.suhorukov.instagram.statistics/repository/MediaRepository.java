package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
