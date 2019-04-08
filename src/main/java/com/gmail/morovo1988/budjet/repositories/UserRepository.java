package com.gmail.morovo1988.budjet.repositories;

import com.gmail.morovo1988.budjet.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    boolean existsUserByEmail(String email);
}
