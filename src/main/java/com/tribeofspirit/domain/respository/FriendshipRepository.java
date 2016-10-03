package com.tribeofspirit.domain.respository;

import com.tribeofspirit.domain.model.Friendship;
import com.tribeofspirit.domain.model.RankingResult;

import java.util.List;

/**
 * Created by tihong on 12/6/15.
 */
public interface FriendshipRepository extends BaseRepository<Friendship> {

    Friendship getByUserAndFollowOpenId(String userOpenId, String followerOpenId);

    List<RankingResult> listRankingInFriends(String weixinOpenId);

}
