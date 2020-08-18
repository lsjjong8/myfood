package com.bit.myfood.controller.api;

import com.bit.myfood.controller.CrudController;
import com.bit.myfood.model.entity.OrderGroup;
import com.bit.myfood.model.network.request.OrderGroupApiRequest;
import com.bit.myfood.model.network.response.OrderGroupApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
}
