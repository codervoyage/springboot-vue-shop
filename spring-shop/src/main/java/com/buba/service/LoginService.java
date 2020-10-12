package com.buba.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoginService {
    HashMap login(String username, String password);

    HashMap getMenuList();

    HashMap updatePassword(Integer userId, String newPassword);
}
