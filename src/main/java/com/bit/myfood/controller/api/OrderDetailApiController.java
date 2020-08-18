package com.bit.myfood.controller.api;

import com.bit.myfood.controller.CrudController;
import com.bit.myfood.model.entity.OrderDetail;
import com.bit.myfood.model.network.request.OrderDetailApiRequest;
import com.bit.myfood.model.network.response.OrderDetailApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
}