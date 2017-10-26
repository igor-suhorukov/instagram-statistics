package com.github.suhorukov.instagram.statistics.repository;

import com.github.suhorukov.instagram.statistics.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
