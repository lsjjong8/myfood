package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.entity.User;
import com.bit.myfood.model.enumclass.UserStatus;
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

public class UserRepositoryTest extends ApplicationTests {
    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    // region CRUD
    @Test
    @Transactional
    void create() {

        try {
            User entity = User.builder()
                    .account("Test Account")
                    .password("1234")
                    .status(UserStatus.REGISTERED)
                    .registeredAt(LocalDateTime.now())
                    .build();

            User newEntity = userRepository.save(entity);
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
            Optional<User> selectedEntity = userRepository.findById(id);
            assertNotNull(selectedEntity.get());

            selectedEntity.get().getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("--- 주문 묶음 ---");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("--- 주문 상세 ---");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("상태 : " + orderDetail.getStatus());
                    System.out.println("수령일 : " + orderDetail.getArrivalDate());

                });
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

            Optional<User> maxRow = userRepository.getFirstByOrderByIdDesc();

            User entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setPassword("0000")
                        .setUpdatedAt(LocalDateTime.now());
            });

            User newEntity = userRepository.save(entity);

            assertEquals(entity, newEntity);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void delete() {
        Optional<Long> before = userRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<User> maxRow = userRepository.getFirstByOrderByIdDesc();

            userRepository.delete(maxRow.get());

            Optional<Long> after = userRepository.countBy();
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