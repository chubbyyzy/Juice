package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.respository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
    @Override
    public User getByOpenId(String openId) {
        Criteria criteria = createCriteria();
        criteria.add(Property.forName("webChatOpenId").eq(openId));
        return (User)criteria.uniqueResult();
    }
}
