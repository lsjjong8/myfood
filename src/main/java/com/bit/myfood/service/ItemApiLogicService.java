package com.bit.myfood.service;

import com.bit.myfood.model.entity.Item;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.ItemApiRequest;
import com.bit.myfood.model.network.response.ItemApiResponse;
import com.bit.myfood.repository.PartnerRepository;
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
public class ItemApiLogicService extends BaseService<ItemApiRequest,ItemApiResponse,Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    // region CRUD
    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        Item entity = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        // 1. data
        ItemApiRequest body = request.getData();

        // 2. id -> item 데이터를 찾고
        Optional<Item> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            // 3. update
            entity
                    .setStatus(body.getStatus())
                    .setName(body.getName())
                    .setTitle(body.getTitle())
                    .setContent(body.getContent())
                    .setPrice(body.getPrice())
                    .setBrandName(body.getBrandName())
                    .setRegisteredAt(LocalDateTime.now())
                    .setPartner(partnerRepository.getOne(body.getPartnerId()));

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
        Optional<Item> optionalEntity = baseRepository.findById(id);

        // 2. repository -> delete
        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected ItemApiResponse response(Item entity) {
        // item -> itemApiResponse

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .name(entity.getName())
                .title(entity.getTitle())
                .content(entity.getContent())
                .price(entity.getPrice())
                .brandName(entity.getBrandName())
                .registeredAt(entity.getRegisteredAt())
                .partnerId(entity.getPartner().getId())
                .build();

        return itemApiResponse;
    }

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> entities = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(itemApiResponseList, pagination);
    }
}
