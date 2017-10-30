package com.github.suhorukov.instagram.statistics.mapper;

import com.github.suhorukov.instagram.statistics.model.Location;
import com.github.suhorukov.instagram.statistics.repository.AccountRepository;
import com.github.suhorukov.instagram.statistics.repository.CommentRepository;
import com.github.suhorukov.instagram.statistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import me.postaddict.instagram.scraper.domain.Account;
import me.postaddict.instagram.scraper.domain.Comment;
import me.postaddict.instagram.scraper.domain.Media;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Mapper {

    private final LocationRepository locationRepository;
    private final AccountRepository accountRepository;
    private final CommentRepository commentRepository;


    public com.github.suhorukov.instagram.statistics.model.Media mapMedia(Media media) {
        com.github.suhorukov.instagram.statistics.model.Media result = new com.github.suhorukov.instagram.statistics.model.Media();
        result.setId(media.getId());
        result.setCaption(media.getCaption());
        result.setCommentsCount(media.getCommentsCount());
        result.setCreatedTime(media.getCreatedTime());
        if(media.getImageUrls()!=null) {
            result.setImageLink(media.getImageUrls().getHigh());
        }
        if(media.getVideoUrls()!=null) {
            result.setVideoLink(media.getVideoUrls().getStandard());
        }
        result.setLikesCount(media.getLikesCount());
        result.setVideoViews(media.getVideoViews());
        result.setVideoViews(media.getVideoViews());
        result.setShortcode(media.getShortcode());
        result.setType(media.getType());
        if(media.getLocation()!=null){
            Location location = locationRepository.findOne(media.getLocation().getId());
            if(location==null) {
                location = new Location();
                BeanUtils.copyProperties(media.getLocation(), location);
                locationRepository.save(location);
            }
            result.setLocation(location);

        }
        if(media.getOwner()!=null){
            Account source = media.getOwner();
            result.setOwner(getAccountOrPersist(source));
        }
        List<Comment> comments = media.getComments();
        if(comments!=null && !comments.isEmpty()){
            result.setComments(comments.stream().map(comment -> {
                com.github.suhorukov.instagram.statistics.model.Comment target = new com.github.suhorukov.instagram.statistics.model.Comment();
                BeanUtils.copyProperties(comment, target);
                return target;

            }).collect(Collectors.toList()));
            commentRepository.save(result.getComments());
        }
        List<Account> likes = media.getLikes();
        if(likes!=null && !likes.isEmpty()){
            result.setLikes(likes.stream().map(this::getAccountOrPersist).collect(Collectors.toList()));
        }
        return result;
    }

    private com.github.suhorukov.instagram.statistics.model.Account getAccountOrPersist(Account source) {
        com.github.suhorukov.instagram.statistics.model.Account account = accountRepository.findOne(source.getId());
        if(account==null){
            account = new com.github.suhorukov.instagram.statistics.model.Account();
            BeanUtils.copyProperties(source, account);
            accountRepository.save(account);
        }
        return account;
    }
}
