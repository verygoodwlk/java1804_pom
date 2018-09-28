package com.qf.shop.dao;

import com.qf.entity.User;

public interface IUserDao {

    User queryByUserName(String username);
}
