package com.bit.myfood.service;

import com.bit.myfood.model.entity.OrderGroup;
import com.bit.myfood.model.entity.User;
import com.bit.myfood.model.enumclass.UserStatus;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.UserApiRequest;
import com.bit.myfood.model.network.response.ItemApiResponse;
import com.bit.myfood.model.network.response.OrderGroupApiResponse;
import com.bit.myfood.model.network.response.UserApiResponse;
import com.bit.myfood.model.network.response.UserOrderInfoApiResponse;
import com.bit.myfood.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest,UserApiResponse,User> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        // user
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);


        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup);

                    // item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item))
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }

    // region CRUD
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest body = request.getData();

        // 2. USER 생성
        User entity = User.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(UserStatus.REGISTERED)
                .email(body.getEmail())
                .phoneNumber(body.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();

        User newEntity = baseRepository.save(entity);

        // 3. 생성된 데이터 -> UserApiResponse Return
        return Header.OK(response(newEntity));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest body = request.getData();

        // 2. id -> user 데이터를 찾고
        Optional<User> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            // 3. update
            entity.setAccount(body.getAccount())
                    .setPassword(body.getPassword())
                    .setStatus(body.getStatus())
                    .setPhoneNumber(body.getPhoneNumber())
                    .setEmail(body.getEmail())
                    .setRegisteredAt(body.getRegisteredAt())
                    .setUnregisteredAt(body.getUnregisteredAt());
            return entity;
        })
        .map(entity -> baseRepository.save(entity))
        .map(newEntity -> response(newEntity))
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optionalEntity = baseRepository.findById(id);

        // 2. repository -> delete
        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    protected UserApiResponse response(User entity) {
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(entity.getId())
                .account(entity.getAccount())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .registeredAt(entity.getRegisteredAt())
                .unregisteredAt(entity.getUnregisteredAt())
                .build();

        return userApiResponse;
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {

        Page<User> entities = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }
}
