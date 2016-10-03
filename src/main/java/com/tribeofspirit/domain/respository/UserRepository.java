package com.tribeofspirit.domain.respository;

import com.tribeofspirit.domain.model.User;

/**
 * Created by Zea Zhang on 11/1/15.
 */
public interface UserRepository extends BaseRepository<User> {
    User getByOpenId(String openId);
}
