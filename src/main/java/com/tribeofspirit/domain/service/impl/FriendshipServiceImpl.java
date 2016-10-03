package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.Friendship;
import com.tribeofspirit.domain.model.RankingResult;
import com.tribeofspirit.domain.respository.FriendshipRepository;
import com.tribeofspirit.domain.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tihong on 12/6/15.
 */

@Service
public class FriendshipServiceImpl extends BaseServiceImpl<Friendship> implements FriendshipService{

    FriendshipRepository friendshipRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        super(friendshipRepository);
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public void insertFriendshipTwoWay(Friendship friendship) {

        Friendship friendshipInDB = friendshipRepository.getByUserAndFollowOpenId(friendship.getUserOpenId(), friendship.getFollowerOpenId());

        if(friendshipInDB == null) {
            insert(friendship);

            Friendship revised = reviseFriendship(friendship);

            insert(revised);
        }

    }

    @Override
    public void insertFriendship(String userOpenId, String followerWeixinId) {

        if(friendshipRepository.getByUserAndFollowOpenId(userOpenId, followerWeixinId) != null) {
            Friendship revised = new Friendship(userOpenId, followerWeixinId);
            insert(revised);
        }

    }

    @Override
    public List<RankingResult> listRankingInFriends(String weixinOpenId){
        return friendshipRepository.listRankingInFriends(weixinOpenId);
    }

    private Friendship reviseFriendship(Friendship friendship) {
        Friendship revised = new Friendship();
        revised.setFollowerOpenId(friendship.getUserOpenId());
        revised.setUserOpenId(friendship.getFollowerOpenId());
        return revised;
    }

    private void insert(Friendship friendship) {
        Friendship existingFriendship = friendshipRepository
                .getByUserAndFollowOpenId(friendship.getUserOpenId(), friendship.getFollowerOpenId());
        if(existingFriendship == null){
            save(friendship);
        }
    }
}
