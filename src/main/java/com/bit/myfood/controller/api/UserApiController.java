package com.bit.myfood.controller.api;

import com.bit.myfood.controller.CrudController;
import com.bit.myfood.model.entity.User;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.request.UserApiRequest;
import com.bit.myfood.model.network.response.UserApiResponse;
import com.bit.myfood.model.network.response.UserOrderInfoApiResponse;
import com.bit.myfood.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest,UserApiResponse,User> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        log.info("{}, {}", "orderInfo : ", id);
        return userApiLogicService.orderInfo(id);
    }
}























//    @Autowired
//    private UserApiLogicService userApiLogicService;
//
//    @Override
//    @PostMapping("")     // /api/user
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        log.info("{}", request);
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")     // /api/user/{id}
//    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
//        log.info("read id : {}", id);
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")         // /api/user
//    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
//        // log.info();
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")  // /api/user/{id}
//    public Header delete(@PathVariable(name = "id") Long id) {
//        log.info("delete : {}", id);
//        return userApiLogicService.delete(id);
//    }