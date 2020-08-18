package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.enumclass.AdminUserStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserRepositoryTest extends ApplicationTests {

    @Autowired
    AdminUserRepository adminUserRepository;

    //region CRUD
    @Test
    @Transactional
    void create() {

        try {
            AdminUser entity = AdminUser.builder()
                    .account("Test")
                    .password("1234")
                    .status(AdminUserStatus.REGISTERED)
                    .role("x")
                    .registeredAt(LocalDateTime.now())
                    .build();

            AdminUser newEntity = adminUserRepository.save(entity);

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
            Optional<AdminUser> selectedEntity = adminUserRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {
            Optional<AdminUser> maxRow = adminUserRepository.getFirstByOrderByIdDesc();

            AdminUser entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setAccount("Test")
                        .setPassword("Test")
                        .setStatus(AdminUserStatus.UNREGISTERED)
                        .setRole("Test");
            });

            AdminUser newEntity = adminUserRepository.save(entity);

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
        Optional<Long> before = adminUserRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<AdminUser> maxRow = adminUserRepository.getFirstByOrderByIdDesc();

            adminUserRepository.delete(maxRow.get());

            Optional<Long> after = adminUserRepository.countBy();
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