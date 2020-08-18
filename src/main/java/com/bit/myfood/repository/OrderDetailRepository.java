package com.bit.myfood.repository;

import com.bit.myfood.model.entity.OrderDetail;
import com.bit.myfood.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Optional<OrderDetail> findById(Long id);

    Optional<OrderDetail> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}
