package com.bit.myfood.service;

import com.bit.myfood.model.entity.OrderDetail;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.OrderDetailApiRequest;
import com.bit.myfood.model.network.response.OrderDetailApiResponse;
import com.bit.myfood.repository.ItemRepository;
import com.bit.myfood.repository.OrderGroupRepository;
import com.bit.myfood.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest,OrderDetailApiResponse, OrderDetail> {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Autowired
    ItemRepository itemRepository;

    // region CRUD
    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        OrderDetail entity = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();

        OrderDetail newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        Optional<OrderDetail> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setStatus(body.getStatus())
                    .setArrivalDate(body.getArrivalDate())
                    .setQuantity(body.getQuantity())
                    .setTotalPrice(body.getTotalPrice())
                    .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                    .setItem(itemRepository.getOne(body.getOrderGroupId()));
            return entity;
        })
                .map(entity -> baseRepository.save(entity))
                .map(newEntity -> response(newEntity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }


    @Override
    public Header delete(Long id) {
        Optional<OrderDetail> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected OrderDetailApiResponse response(OrderDetail entity) {
        // orderDetail -> orderDetailApiResponse

        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .arrivalDate(entity.getArrivalDate())
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .orderGroupId(entity.getOrderGroup().getId())
                .itemId(entity.getItem().getId())
                .build();

        return orderDetailApiResponse;
    }

    @Override
    public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
        Page<OrderDetail> entities = baseRepository.findAll(pageable);

        List<OrderDetailApiResponse> orderDetailApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(orderDetailApiResponseList, pagination);
    }
}

