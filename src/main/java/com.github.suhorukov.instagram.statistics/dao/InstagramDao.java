package com.github.suhorukov.instagram.statistics.dao;

import com.github.suhorukov.instagram.statistics.repository.*;
import lombok.AllArgsConstructor;
import me.postaddict.instagram.scraper.model.*;

import javax.inject.Named;
import javax.inject.Singleton;

@Named("InstagramDao")
@Singleton
@AllArgsConstructor
public class InstagramDao {
    private CommentRepository commentRepository;
    private final MediaRepository mediaRepository;
    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;
    private CarouselResourceRepository carouselResourceRepository;

    public void saveAccount(me.postaddict.instagram.scraper.model.Account account) {
        if(account==null) return;
        Account dbAccount = accountRepository.findOne(account.getId());
        if(dbAccount==null || account.getProfilePicUrlHd()!=null){
            accountRepository.save(account);
            if(account.getMedia()!=null && account.getMedia().getNodes()!=null) {
                account.getMedia().getNodes().forEach(media -> media.setOwner(account));
                account.getMedia().getNodes().forEach(this::saveMedia);
            }
        }
    }

    public void saveMedia(me.postaddict.instagram.scraper.model.Media media) {
        if(media==null) return;
        Media dbMedia = mediaRepository.findOne(media.getId());
        if(dbMedia==null || media.getCaption()!=null) {
            Location location = media.getLocation();
            if (location != null && locationRepository.findOne(location.getId()) == null) {
                locationRepository.save(location);
            }
            if (media.getOwner() != null) {
                getOrSaveAccount(media.getOwner());
            }
            if (media.getFirstLikes() != null) {
                media.getFirstLikes().forEach(this::getOrSaveAccount);
            }
            if (media.getCommentPreview() != null && media.getCommentPreview().getNodes()!=null) {
                media.setFirstComments(media.getCommentPreview().getNodes());
                media.getFirstComments().forEach(this::getOrSaveComment);
            }
            if (media.getCarouselMedia() != null) {
                media.getCarouselMedia().forEach(this::getOrSaveCarouselResource);
            }
            mediaRepository.save(media);
        }
    }

    private void getOrSaveComment(Comment comment) {
        Comment dbComment = commentRepository.findOne(comment.getId());
        if(dbComment==null){
            getOrSaveAccount(comment.getOwner());
            commentRepository.save(comment);
        }
    }

    private void getOrSaveCarouselResource(CarouselResource carouselResource) {
        CarouselResource carouselResourceDb = carouselResourceRepository.findOne(carouselResource.getShortcode());
        if(carouselResourceDb==null){
            carouselResourceRepository.save(carouselResource);
        }
    }

    private me.postaddict.instagram.scraper.model.Account getOrSaveAccount(me.postaddict.instagram.scraper.model.Account account) {
        me.postaddict.instagram.scraper.model.Account dbAccount = accountRepository.findOne(account.getId());
        if(dbAccount == null){
            return accountRepository.save(account);
        }
        return dbAccount;
    }
}
