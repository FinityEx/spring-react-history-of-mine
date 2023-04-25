package com.endtoend.historyOfMine.repositories;

import com.endtoend.historyOfMine.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByUsername(String username);
    public void deleteByUsername(String username);
    public boolean existsByUsername(String username);

}
