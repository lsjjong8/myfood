package com.bit.myfood.controller.api;

import com.bit.myfood.controller.CrudController;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.network.request.AdminUserApiRequest;
import com.bit.myfood.model.network.request.CookRcpApiRequest;
import com.bit.myfood.model.network.response.AdminUserApiResponse;
import com.bit.myfood.model.network.response.CookRcpApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {
}