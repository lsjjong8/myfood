package com.bit.myfood.repository;

import com.bit.myfood.model.entity.Item;
import com.bit.myfood.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);

    Optional<Item> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}
