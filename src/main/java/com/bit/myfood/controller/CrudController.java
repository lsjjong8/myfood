package com.bit.myfood.controller;

import com.bit.myfood.ifs.CrudInterface;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected BaseService<Req,Res,Entity> baseService;

    @GetMapping("")
    public Header<List<Res>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("{}, {}", "search : ", pageable);
        return baseService.search(pageable);
    }

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        log.info("{}, {}", "create : ", request);
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        log.info("{}, {}", "read : ", id);
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        log.info("{}, {}", "update : ", request);
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("{}, {}", "delete : ", id);
        return baseService.delete(id);
    }

}