package com.github.suhorukov.instagram.statistics;

import com.github.suhorukov.instagram.statistics.mapper.Mapper;
import com.github.suhorukov.instagram.statistics.model.Location;
import com.github.suhorukov.instagram.statistics.repository.AccountRepository;
import com.github.suhorukov.instagram.statistics.repository.CommentRepository;
import com.github.suhorukov.instagram.statistics.repository.LocationRepository;
import com.github.suhorukov.instagram.statistics.repository.MediaRepository;
import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.domain.Account;
import me.postaddict.instagram.scraper.domain.Comment;
import me.postaddict.instagram.scraper.domain.Media;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = SpringContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class GetAccountTest {

    private static ThreadLocal<Instagram> instagram = ThreadLocal.withInitial(() -> new Instagram(new OkHttpClient()));

    @Inject
    private MediaRepository mediaRepository;

    @Inject
    private LocationRepository locationRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CommentRepository commentRepository;

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
        Media media = instagram.get().getMediaByCode("BZFQ0MXgcdK");
        com.github.suhorukov.instagram.statistics.model.Media result = new Mapper(locationRepository, accountRepository, commentRepository).mapMedia(media);
        mediaRepository.save(result);
        List<Media> landscape = instagram.get().getMediasByTag("landscape", 12);
        landscape.size();
    }

}
