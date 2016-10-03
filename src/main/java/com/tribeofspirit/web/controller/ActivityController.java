package com.tribeofspirit.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribeofspirit.domain.model.Activity;
import com.tribeofspirit.domain.model.attribute.ActivityType;
import com.tribeofspirit.domain.service.ActivityService;
import com.tribeofspirit.domain.service.Result;
import com.tribeofspirit.web.interceptor.WeiXinOpenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Zea Zhang on 12/12/15.
 */
@Controller
public class ActivityController {
    public ActivityService getActivityService() {
        return activityService;
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    ActivityService activityService;

    @RequestMapping(value = "/activity/{scanningId}", method = RequestMethod.POST)
    public String tagActivity(
            @WeiXinOpenId String weixinOpenId,
            @PathVariable String scanningId,
            @RequestParam("activitytype")String activityType,
            @RequestParam("activitycomments")String activityComments) throws JsonProcessingException {
        Activity activity = new Activity();
        activity.setActivityType(ActivityType.valueOf(activityType));
        activity.setActivityComments(activityComments);
        Result<Activity> result = activityService.save(activity);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }

}
