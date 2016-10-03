package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.Friendship;
import com.tribeofspirit.domain.model.RankingResult;
import com.tribeofspirit.domain.respository.FriendshipRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tihong on 12/6/15.
 */
@Repository
public class FriendshipRepositoryImpl extends BaseRepositoryImpl<Friendship> implements FriendshipRepository{
    @Override
    public Friendship getByUserAndFollowOpenId(String userOpenId, String followerOpenId) {

        Criteria criteria = createCriteria();
        criteria.add(Property.forName("followerOpenId").eq(followerOpenId));
        criteria.add(Property.forName("userOpenId").eq(userOpenId));
        return (Friendship)criteria.uniqueResult();
    }

    @Override
    public List<RankingResult> listRankingInFriends(String weixinOpenId) {

       SQLQuery query = getSession().createSQLQuery(
               "select\n" +
                       "                result.image_name,\n" +
                       "                result.mood_score ,\n" +
                       "                _user.photo_Uri,\n" +
                       "                _user.nick_name,\n" +
                       "                friends.follower_open_id\n" +
                       "            from\n" +
                       "                (select\n" +
                       "                    result.*\n" +
                       "                from\n" +
                       "                    `model_face_scanning_result` result,\n" +
                       "                    (SELECT\n" +
                       "                        MAX(id) id,\n" +
                       "                        MAX(create_time)\n" +
                       "                    FROM\n" +
                       "                        MODEL_face_scanning_result\n" +
                       "                    GROUP BY\n" +
                       "                        weixin_open_id) latest\n" +
                       "                where\n" +
                       "                    result.id=latest.id\n" +
                       "                ) result,                MODEL_friendship friends,         MODEL_user _user\n" +
                       "            where\n" +
                       "                result.weixin_open_id =_user.web_chat_open_id\n" +
                       "                and result.weixin_open_id = friends.follower_open_id\n" +
                       "\t\t\t    and friends.user_open_id = :weixinOpenId\n" +
                       "            order by result.mood_score desc;");

        query.setParameter("weixinOpenId", weixinOpenId);

        query.setResultTransformer(Transformers.aliasToBean(RankingResult.class));

        return query.list();
    }
}
