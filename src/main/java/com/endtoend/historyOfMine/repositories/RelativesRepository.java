package com.endtoend.historyOfMine.repositories;

import com.endtoend.historyOfMine.models.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelativesRepository extends JpaRepository<Relative, UUID> {
    public Optional<Relative> findByLastName(String lastName);
    @Query(value = "SELECT * FROM relatives r WHERE r.name = :name AND r.lastName = :lastName AND r.date_of_birth = :dateOfBirth",
            nativeQuery = true)
    public Optional<Relative> alreadyExists(String name, String lastName, Date dateOfBirth);
}
