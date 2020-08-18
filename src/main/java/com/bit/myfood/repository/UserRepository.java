package com.bit.myfood.repository;

import com.bit.myfood.model.entity.User;
import com.bit.myfood.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}