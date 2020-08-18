package com.bit.myfood.service;

import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.CategoryApiRequest;
import com.bit.myfood.model.network.response.CategoryApiResponse;
import com.bit.myfood.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest,CategoryApiResponse, Category> {

    // region CRUD
    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        Category entity = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .build();

        Category newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        Optional<Category> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setType(body.getType())
                    .setTitle(body.getTitle());
            return entity;
        })
                .map(entity -> baseRepository.save(entity))
                .map(newEntity -> response(newEntity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected CategoryApiResponse response(Category entity) {
        // category -> categoryApiResponse

        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .title(entity.getTitle())
                .build();

        return categoryApiResponse;
    }

    @Override
    public Header<List<CategoryApiResponse>> search(Pageable pageable) {
        Page<Category> entities = baseRepository.findAll(pageable);

        List<CategoryApiResponse> categoryApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(categoryApiResponseList, pagination);
    }
}