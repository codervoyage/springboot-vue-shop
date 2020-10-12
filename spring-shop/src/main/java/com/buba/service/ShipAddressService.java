package com.buba.service;

import java.util.HashMap;

public interface ShipAddressService {
    HashMap limitAddress(Integer currentPage, Integer pageSize);

    int getNomber();
}
