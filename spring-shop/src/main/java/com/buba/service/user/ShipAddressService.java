package com.buba.service.user;

import java.util.HashMap;

public interface ShipAddressService {
    HashMap limitAddress(Integer currentPage, Integer pageSize);

    int getNomber();

    HashMap selectOne(Integer userID, String userName, Integer currentPage, Integer pageSize);
}
