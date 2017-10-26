package com.github.suhorukov.instagram.statistics;

import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.domain.Account;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountTest {
    private static ThreadLocal<Instagram> instagram = ThreadLocal.withInitial(() -> new Instagram(new OkHttpClient()));
    @Test
    public void getAccount() throws Exception {
        Account account = instagram.get().getAccountByUsername("kevin");
        assertThat(account).isNotNull();
        com.github.suhorukov.instagram.statistics.model.Account result = new com.github.suhorukov.instagram.statistics.model.Account();
        BeanUtils.copyProperties(account, result);
        assertThat(result.getUsername()).isEqualTo(account.username);

    }
}
