package com.tribeofspirit.domain.respository.hibernate;

import com.tribeofspirit.domain.model.Activity;
import com.tribeofspirit.domain.respository.ActivityRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zea Zhang on 11/1/15.
 */
@Repository
public class ActivityRepositoryImpl extends BaseRepositoryImpl<Activity> implements ActivityRepository{
}
