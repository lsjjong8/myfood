package com.bit.myfood.service;

import com.bit.myfood.model.entity.OrderGroup;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.OrderGroupApiRequest;
import com.bit.myfood.model.network.response.OrderGroupApiResponse;
import com.bit.myfood.repository.UserRepository;
import com.bit.myfood.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest,OrderGroupApiResponse, OrderGroup> {

    @Autowired
    UserRepository userRepository;

    // region CRUD
    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup entity = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        Optional<OrderGroup> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setStatus(body.getStatus())
                    .setOrderType(body.getOrderType())
                    .setRevAddress(body.getRevAddress())
                    .setRevName(body.getRevName())
                    .setPaymentType(body.getPaymentType())
                    .setTotalPrice(body.getTotalPrice())
                    .setTotalQuantity(body.getTotalQuantity())
                    .setOrderAt(body.getOrderAt())
                    .setArrivalDate(body.getArrivalDate())
                    .setUser(userRepository.getOne(body.getUserId()));
            return entity;
        })
                .map(entity -> baseRepository.save(entity))
                .map(newEntity -> response(newEntity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected OrderGroupApiResponse response(OrderGroup entity) {
        // orderGroup -> orderGroupApiResponse

        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .orderType(entity.getOrderType())
                .revAddress(entity.getRevAddress())
                .revName(entity.getRevName())
                .paymentType(entity.getPaymentType())
                .totalPrice(entity.getTotalPrice())
                .totalQuantity(entity.getTotalQuantity())
                .orderAt(entity.getOrderAt())
                .arrivalDate(entity.getArrivalDate())
                .userId(entity.getUser().getId())
                .build();

        return orderGroupApiResponse;
    }

    @Override
    public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {

        Page<OrderGroup> entities = baseRepository.findAll(pageable);

        List<OrderGroupApiResponse> orderGroupApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(orderGroupApiResponseList, pagination);
    }

}