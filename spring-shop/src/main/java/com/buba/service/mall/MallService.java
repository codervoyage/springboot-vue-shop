package com.buba.service.mall;

import java.util.HashMap;

public interface MallService {
    HashMap getArea();

    HashMap getBrand(Integer id, String name);

    HashMap deleteBrand(Integer id);

    HashMap addBrand(String name, String test, String img, float low);
}
