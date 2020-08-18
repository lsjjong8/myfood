package com.bit.myfood.repository;

import com.bit.myfood.model.entity.OrderGroup;
import com.bit.myfood.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {

    Optional<OrderGroup> findById(Long id);

    Optional<OrderGroup> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}
