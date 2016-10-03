package com.tribeofspirit.domain.service.impl;

import com.tribeofspirit.domain.model.Activity;
import com.tribeofspirit.domain.respository.ActivityRepository;
import com.tribeofspirit.domain.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

    ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        super(activityRepository);
        this.activityRepository = activityRepository;
    }
}
