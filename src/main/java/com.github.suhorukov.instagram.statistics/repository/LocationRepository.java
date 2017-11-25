package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long>{
}
