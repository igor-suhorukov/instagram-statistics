package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {
}
