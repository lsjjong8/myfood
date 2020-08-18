package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CookRcpRepositoryTest extends ApplicationTests {

    @Autowired
    CookRcpRepository cookRcpRepository;

    @Autowired
    AdminUserRepository adminUserRepository;

    // region CRUD
    @Test
    @Transactional
    void create() {

        try {
            Optional<CookRcp> maxRow = cookRcpRepository.getFirstByOrderByIdDesc();

            CookRcp entity = CookRcp.builder()
                    .rcpNm("Test")
                    .createdAt(LocalDateTime.now())
                    .adminUser(adminUserRepository.getOne(1L))
                    .build();

            CookRcp newEntity = cookRcpRepository.save(entity);

            assertEquals(entity, newEntity);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
    }

    @Test
    void read() {
        Long id = 1L;

        try {
            Optional<CookRcp> selectedEntity = cookRcpRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<CookRcp> maxRow = cookRcpRepository.getFirstByOrderByIdDesc();

            CookRcp entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setRcpNm("Test2")
                        .setUpdatedAt(LocalDateTime.now());
            });

            CookRcp newEntity = cookRcpRepository.save(entity);

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
        Optional<Long> before = cookRcpRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<CookRcp> maxRow = cookRcpRepository.getFirstByOrderByIdDesc();

            cookRcpRepository.delete(maxRow.get());

            Optional<Long> after = cookRcpRepository.countBy();
            Long afterCount = after.get();

            assertEquals(beforeCount, afterCount + 1L);

        } catch (ConstraintViolationException e){
            e.printStackTrace();
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion
}