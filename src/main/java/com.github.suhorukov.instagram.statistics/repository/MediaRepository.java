package com.github.suhorukov.instagram.statistics.repository;

import com.github.suhorukov.instagram.statistics.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long>{
}
