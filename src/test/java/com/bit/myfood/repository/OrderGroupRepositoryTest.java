package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.entity.OrderGroup;
import com.bit.myfood.model.enumclass.OrderStatus;
import com.bit.myfood.model.enumclass.OrderType;
import org.hibernate.exception.ConstraintViolationException;
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

class OrderGroupRepositoryTest extends ApplicationTests {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Autowired
    UserRepository userRepository;

    // region CRUD
    @Test
    @Transactional
    void create() {

        try {
            OrderGroup entity = OrderGroup.builder()
                    .status(OrderStatus.ORDERING)
                    .orderType(OrderType.EACH)
                    .build();

            entity.setUser(userRepository.findById(1L).get());

            OrderGroup newEntity = orderGroupRepository.save(entity);

            assertEquals(entity, newEntity);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void read() {
        Long id = 1L;

        try {
            Optional<OrderGroup> selectedEntity = orderGroupRepository.findById(id);
            assertNotNull(selectedEntity.get());

            selectedEntity.get().getOrderDetailList().stream().forEach(orderGroup -> {
                System.out.println("상태 : " + orderGroup.getStatus());
                System.out.println("수령일 : " + orderGroup.getArrivalDate());
            });

            selectedEntity.ifPresent(entity -> {
                System.out.println("Result : " + entity);
            });

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<OrderGroup> maxRow = orderGroupRepository.getFirstByOrderByIdDesc();

            OrderGroup entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setStatus(OrderStatus.COMPLETE)
                        .setUpdatedAt(LocalDateTime.now());
            });

            OrderGroup newEntity = orderGroupRepository.save(entity);

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
        Optional<Long> before = orderGroupRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<OrderGroup> maxRow = orderGroupRepository.getFirstByOrderByIdDesc();

            orderGroupRepository.delete(maxRow.get());

            Optional<Long> after = orderGroupRepository.countBy();
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