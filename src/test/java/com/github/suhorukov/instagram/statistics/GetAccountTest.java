package com.github.suhorukov.instagram.statistics;

import com.github.suhorukov.instagram.statistics.dao.InstagramDao;
import com.github.suhorukov.instagram.statistics.repository.*;
import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.model.*;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = SpringContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class GetAccountTest {

    private static ThreadLocal<Instagram> instagram = ThreadLocal.withInitial(() -> new Instagram(new OkHttpClient()));
    //![](https://habrastorage.org/webt/mx/8z/3r/mx8z3r07hkes31i_tpaqv-mjzyq.png)

    @Inject
    private InstagramDao instagramDao;

    @Inject
    private MediaRepository mediaRepository;

    @Inject
    private LocationRepository locationRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private CarouselResourceRepository carouselResourceRepository;

    @Inject
    private TagRepository tagRepository;

    @Test
    public void getAccount() throws Exception {
        Account account = instagram.get().getAccountByUsername("kevin");
        assertThat(account).isNotNull();

        instagramDao.saveAccount(account);

        System.out.println(account.getId());
    }


    @Test
    public void getMedia() throws Exception {
        Media media = instagram.get().getMediaByCode("BaKLiFugkQa");
        instagramDao.saveMedia(media);
        assertThat(media).isNotNull();

        Tag tag = instagram.get().getTagByName("moscow");
        tagRepository.save(tag);

    }

}
