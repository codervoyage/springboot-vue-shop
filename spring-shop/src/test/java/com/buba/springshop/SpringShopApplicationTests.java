package com.buba.springshop;

import com.buba.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class SpringShopApplicationTests {
    @Test
    void contextLoads() {
        int u =1;
        System.out.println(u);
        a(u);
        System.out.println(u);
    }

    private void a (Integer u){
        u=2;
    }
}


