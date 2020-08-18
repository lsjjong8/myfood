package com.bit.myfood.repository;

import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findById(Long id);

    Optional<AdminUser> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}
