package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.respository.ScanningResultRepository;
import com.tribeofspirit.domain.respository.UserRepository;
import com.tribeofspirit.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;

    }

    @Override
    public User getByOpenId(String openId) {
        return userRepository.getByOpenId(openId);
    }
}
