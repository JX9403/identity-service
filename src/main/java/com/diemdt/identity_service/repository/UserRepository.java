package com.diemdt.identity_service.repository;

import com.diemdt.identity_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Page<User> findAll (Pageable pageable) ;
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username) ;

}
