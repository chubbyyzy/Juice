package com.tribeofspirit.web.controller;

import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.service.FriendshipService;
import com.tribeofspirit.domain.service.ScanningResultService;
import com.tribeofspirit.domain.service.UserService;
import com.tribeofspirit.web.interceptor.WeiXinOpenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tihong on 12/7/15.
 */

@Controller
public class RankingController {

    private UserService userService;

    private ScanningResultService scanningResultService;

    private FriendshipService friendshipService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendshipService(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @Autowired
    public void setScanningResultService(ScanningResultService scanningResultService) {
        this.scanningResultService = scanningResultService;
    }

    @RequestMapping(value="/ranking", method = RequestMethod.GET)
    public ModelAndView ranking(@WeiXinOpenId String weixinOpenId){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ranking");
        modelAndView.addObject("rankingResults", friendshipService.listRankingInFriends(weixinOpenId));

        User user = userService.getByOpenId(weixinOpenId);
        modelAndView.addObject("user", user);

        FaceScanningResult latestResult = scanningResultService.getLatestResult(weixinOpenId);
        modelAndView.addObject("userResult", latestResult);

        return modelAndView;
    }
}
