package com.github.suhorukov.instagram.statistics.repository;

import me.postaddict.instagram.scraper.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
