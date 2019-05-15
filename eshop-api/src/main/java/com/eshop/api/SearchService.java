package com.eshop.api;

import com.eshop.entity.User;

public interface SearchService {

    public String searchTest();

    public User searchByKeyword(String key);

    public void indexUser(User user);

    public void delUser(Long id);
}
