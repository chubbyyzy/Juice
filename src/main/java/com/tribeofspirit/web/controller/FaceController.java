package com.tribeofspirit.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribeofspirit.domain.model.FaceScanningResult;
import com.tribeofspirit.domain.model.Friendship;
import com.tribeofspirit.domain.model.User;
import com.tribeofspirit.domain.service.FriendshipService;
import com.tribeofspirit.domain.service.ScanningResultService;
import com.tribeofspirit.domain.service.UserService;
import com.tribeofspirit.external.sightcorp.FaceRecognition;
import com.tribeofspirit.external.sightcorp.entity.Person;
import com.tribeofspirit.external.weixin.WXAPI;
import com.tribeofspirit.external.weixin.ConfigBuilder;
import com.tribeofspirit.external.weixin.WXConfig;
import com.tribeofspirit.utils.FaceScanningUtil;
import com.tribeofspirit.web.interceptor.WeiXinOpenId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

/**
 * Author : gonwang
 * Create time : 20.10.2015.
 */
@Controller
public class FaceController {

    private ConfigBuilder configBuilder;

    private FaceRecognition faceRecognition;

    private UserService userService;

    private WXAPI WXAPI;

    private FriendshipService friendshipService;

    @Autowired
    public void setScanningResultService(ScanningResultService scanningResultService) {
        this.scanningResultService = scanningResultService;
    }

    private ScanningResultService scanningResultService;

    @Autowired
    public void setFaceRecognition(FaceRecognition faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    @Autowired
    public void setConfigBuilder(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
    }

    @Autowired
    public void setWXAPI(WXAPI WXAPI) {
        this.WXAPI = WXAPI;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setFriendshipService(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String entry(@WeiXinOpenId String weixinOpenId) {

        User user = WXAPI.getUserInfo(weixinOpenId);

        User userInDB = userService.getByOpenId(weixinOpenId);
        if(userInDB == null) {
            userService.save(user);
            //follow user self
            friendshipService.insertFriendship(weixinOpenId, weixinOpenId);
        }else{
            friendshipService.insertFriendship(weixinOpenId, weixinOpenId);
            //TODO, update the user
        }

        return "face";
    }

    @RequestMapping(value = "/wxConfig", method = RequestMethod.GET)
    @ResponseBody
    public WXConfig generateWXConfig(@RequestParam("url")String url) {

        return configBuilder.buildFor(url);
    }

    @RequestMapping(value = "/detect/{mediaId}", method = RequestMethod.POST)
    @ResponseBody
    public String detect(@PathVariable String mediaId, @WeiXinOpenId String weixinOpenId) throws JsonProcessingException {

        File photo = WXAPI.getImage(mediaId);

        Person person = faceRecognition.detect(photo);

        ObjectMapper mapper = new ObjectMapper();

        if(person != null){
            FaceScanningResult faceScanningResult = FaceScanningUtil.convertPersonToFaceScanningResult(person);
            faceScanningResult.setMideaId(mediaId);
            faceScanningResult.setWeixinOpenId(weixinOpenId);
            faceScanningResult.setImageName(person.getImageName());
            scanningResultService.save(faceScanningResult);

            WXAPI.sendResultMessage(weixinOpenId, faceScanningResult);

            person.setScanningId(faceScanningResult.getId());

            return mapper.writeValueAsString(person);
        }

        return null;
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public ModelAndView share(@RequestParam("scanningid")String scanningId, @WeiXinOpenId String followerOpenId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share");
        FaceScanningResult faceScanningResult = scanningResultService.get(new Long(scanningId));
        modelAndView.addObject("scanningResult", faceScanningResult);

        return modelAndView;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView getResult(@RequestParam("scanningid")String scanningId, @WeiXinOpenId String followerOpenId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        FaceScanningResult faceScanningResult = scanningResultService.prepare(new Long(scanningId));

        String userWeiXinOpenId = faceScanningResult.getWeixinOpenId();

        if(!StringUtils.equals(followerOpenId, userWeiXinOpenId)){
            Friendship friendship = new Friendship();
            friendship.setUserOpenId(userWeiXinOpenId);
            friendship.setFollowerOpenId(followerOpenId);
            friendshipService.insertFriendshipTwoWay(friendship);
        }

        modelAndView.addObject("scanningResult", faceScanningResult);

        return modelAndView;
    }

    @RequestMapping(value = "/detect", method = RequestMethod.GET)
    public ModelAndView goToDetect(@RequestParam("mediaId")String mediaId, UriComponentsBuilder uriComponentsBuilder) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "detect" );
        modelAndView.addObject("mediaId", mediaId);

        String hostUrl = uriComponentsBuilder.toUriString();
        modelAndView.addObject("hostUrl", hostUrl);

        return modelAndView;
    }


    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test(){return "test";}
}
