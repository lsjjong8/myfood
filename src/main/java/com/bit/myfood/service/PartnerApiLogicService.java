package com.bit.myfood.service;

import com.bit.myfood.model.entity.Partner;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.PartnerApiRequest;
import com.bit.myfood.model.network.response.PartnerApiResponse;
import com.bit.myfood.repository.CategoryRepository;
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
public class PartnerApiLogicService extends BaseService<PartnerApiRequest,PartnerApiResponse, Partner> {

    @Autowired
    CategoryRepository categoryRepository;

    // region CRUD
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        Partner entity = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();

        Partner newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        Optional<Partner> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setName(body.getName())
                    .setStatus(body.getStatus())
                    .setAddress(body.getAddress())
                    .setCallCenter(body.getCallCenter())
                    .setPartnerNumber(body.getPartnerNumber())
                    .setBusinessNumber(body.getBusinessNumber())
                    .setCeoName(body.getCeoName())
                    .setRegisteredAt(LocalDateTime.now())
                    .setCategory(categoryRepository.getOne(body.getCategoryId()));
            return entity;
        })
                .map(entity -> baseRepository.save(entity))
                .map(newEntity -> response(newEntity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected PartnerApiResponse response(Partner entity) {
        // item -> itemApiResponse

        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .status(entity.getStatus())
                .address(entity.getAddress())
                .callCenter(entity.getCallCenter())
                .partnerNumber(entity.getPartnerNumber())
                .businessNumber(entity.getBusinessNumber())
                .ceoName(entity.getCeoName())
                .registeredAt(entity.getRegisteredAt())
                .unregisteredAt(entity.getUnregisteredAt())
                .categoryId(entity.getCategory().getId())
                .build();

        return partnerApiResponse;
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {

        Page<Partner> entities = baseRepository.findAll(pageable);

        List<PartnerApiResponse> partnerApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(partnerApiResponseList, pagination);
    }
}
