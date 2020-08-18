package com.bit.myfood.sample;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.enumclass.AdminUserStatus;
import com.bit.myfood.repository.AdminUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AdminUserSample extends ApplicationTests {

    @Autowired
    private AdminUserRepository categoryRepository;

    @Test
    public void createSample(){

        for(int i = 0; i < 1; i++){

            AdminUser create = AdminUser.builder()
                    .id(1L)
                    .account("admin")
                    .password("1234")
                    .status(AdminUserStatus.REGISTERED)
                    .role("admin")
                    .loginFailCount(0)
                    .build();

            System.out.println(create);
            categoryRepository.save(create);
        }
    }
}