package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.entity.OrderDetail;
import com.bit.myfood.model.enumclass.OrderStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderDetailRepositoryTest extends ApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Test
    @Transactional
    void create() {

        try {

            OrderDetail entity = OrderDetail.builder()
                    .status(OrderStatus.ORDERING)
                    .arrivalDate(LocalDateTime.now().plusDays(2))
                    .build();

            entity.setOrderGroup(orderGroupRepository.findById(1L).get());
            entity.setItem(itemRepository.findById(1L).get());

            OrderDetail newEntity = orderDetailRepository.save(entity);

            assertEquals(entity, newEntity);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    void read() {
        Long id = 1L;

        try {
            Optional<OrderDetail> selectedEntity = orderDetailRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<OrderDetail> maxRow = orderDetailRepository.getFirstByOrderByIdDesc();

            OrderDetail entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setStatus(OrderStatus.COMPLETE)
                        .setUpdatedAt(LocalDateTime.now());
            });

            OrderDetail newEntity = orderDetailRepository.save(entity);

            assertEquals(entity, newEntity);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void delete() throws DataIntegrityViolationException {
        Optional<Long> before = orderDetailRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<OrderDetail> maxRow = orderDetailRepository.getFirstByOrderByIdDesc();

            orderDetailRepository.delete(maxRow.get());

            Optional<Long> after = orderDetailRepository.countBy();
            Long afterCount = after.get();

            assertEquals(beforeCount, afterCount + 1L);

        } catch (ConstraintViolationException e){
            e.printStackTrace();
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
        } catch (javax.persistence.EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion
}