package com.github.suhorukov.instagram.statistics;

import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.domain.Account;
import me.postaddict.instagram.scraper.domain.Media;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.List;

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

    @Test
    public void getMedia() throws Exception {
        instagram.get().getMediaByCode("BanqgIKAQFU");
        List<Media> landscape = instagram.get().getMediasByTag("landscape", 12);
        landscape.size();
    }
}
