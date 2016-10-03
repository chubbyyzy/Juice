package com.tribeofspirit.domain.service;


import com.tribeofspirit.domain.model.Friendship;
import com.tribeofspirit.domain.model.RankingResult;

import java.util.List;

/**
 * Created by tihong on 12/6/15.
 */
public interface FriendshipService extends BaseService<Friendship>{

    void insertFriendshipTwoWay(Friendship friendship);

    void insertFriendship(String userOpenId, String followreWeixinId);

    List<RankingResult> listRankingInFriends(String weixinOpenId);

}
