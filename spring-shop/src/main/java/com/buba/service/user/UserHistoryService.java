package com.buba.service.user;

import java.util.HashMap;

public interface UserHistoryService {
    HashMap limitHistory(Integer currentPage, Integer pageSize);

    int getNomber();

    HashMap selectOne(Integer searchID, String keyword, Integer currentPage, Integer pageSize);
}
