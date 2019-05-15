package com.eshop.search.controller;

import com.eshop.api.SearchService;
import com.eshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s")
public class IndexController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/test")
    public String test(){
        User user = new User();
        user.setId(1L);
        user.setName("james");
        user.setPwd("1234");
        searchService.indexUser(user);
        return "success";
    }

    @RequestMapping("/testSearch")
    public String testSearch(String key){
        searchService.searchByKeyword(key);
        return "";
    }
}
