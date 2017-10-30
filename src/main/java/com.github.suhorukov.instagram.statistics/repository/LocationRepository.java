package com.github.suhorukov.instagram.statistics.repository;

import com.github.suhorukov.instagram.statistics.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
