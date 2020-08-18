package com.bit.myfood.service;

import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.AdminUserApiRequest;
import com.bit.myfood.model.network.response.AdminUserApiResponse;
import com.bit.myfood.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest,AdminUserApiResponse, AdminUser> {

    //region CRUD
    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        AdminUser entity = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .loginFailCount(body.getLoginFailCount())
                .registeredAt(LocalDateTime.now())
                .build();

        AdminUser newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        Optional<AdminUser> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setAccount(body.getAccount())
                    .setPassword(body.getPassword())
                    .setStatus(body.getStatus())
                    .setRole(body.getRole())
                    .setRegisteredAt(LocalDateTime.now())
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
        Optional<AdminUser> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected AdminUserApiResponse response(AdminUser entity) {
        // adminUser -> adminUserApiResponse

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(entity.getId())
                .account(entity.getAccount())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .role(entity.getRole())
                .lastLoginAt(entity.getLastLoginAt())
                .loginFailCount(entity.getLoginFailCount())
                .passwordUpdatedAt(entity.getPasswordUpdatedAt())
                .registeredAt(entity.getRegisteredAt())
                .unregisteredAt(entity.getUnregisteredAt())
                .build();

        return adminUserApiResponse;
    }

    @Override
    public Header<List<AdminUserApiResponse>> search(Pageable pageable) {

        Page<AdminUser> entities = baseRepository.findAll(pageable);

        List<AdminUserApiResponse> AdminUserApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(AdminUserApiResponseList, pagination);
    }
}