package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String>{
}
