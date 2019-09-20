package com.vironit.basumatarau.socialSsoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class StaticController {

    @RequestMapping("/")
    String getStaticContent(HttpServletRequest request, HttpServletResponse response){
        return "<a href=\"https://justforlulz.tk/oauth2/authorize/google?redirect_uri=https://justforlulz.tk/user/me\">Who am I?</a>";
    }

}
