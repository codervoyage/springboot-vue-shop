package com.buba.service.user;

import java.util.HashMap;

public interface FeedBackService {
    HashMap limitBack(Integer currentPage, Integer pageSize);

    int getNomber();

    HashMap selectOne(Integer feedBackID, String userName, Integer currentPage, Integer pageSize);
}
