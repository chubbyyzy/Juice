package com.tribeofspirit.domain.service;

import com.tribeofspirit.domain.model.User;

/**
 * Created by Zea Zhang on 11/1/15.
 */
public interface UserService extends BaseService<User> {

    User getByOpenId(String openId);

}
