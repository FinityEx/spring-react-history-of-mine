package com.endtoend.historyOfMine.repositories;

import com.endtoend.historyOfMine.models.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RelativesRepository extends JpaRepository<Relative, UUID> {
    public Optional<Relative> findByLastName(String lastName);
    @Query(value = "SELECT * FROM relatives r WHERE r.name = :name AND r.last_name = :lastName AND r.birth = :dateOfBirth",
            nativeQuery = true)
    public Optional<Relative> alreadyExists(String name, String lastName, Date dateOfBirth);
    public List<Relative> getRelativesByUserId(Integer id);
    @Query(value = "SELECT * FROM relatives r JOIN tbl_relatives t ON r.id = t.r_id WHERE t.relative_of_id = :id",
    nativeQuery = true)
    public List<Relative> getRelatives(Integer id);
    @Query(value = "SELECT * FROM relatives r JOIN tbl_relatives t ON r.id = t.r_id WHERE t.relative_of_id = :id AND r.as = 'PARENT'",
            nativeQuery = true)
    public List<Relative> getParents(Integer id);
    @Query(value = "SELECT * FROM relatives r JOIN tbl_relatives t ON r.id = t.r_id WHERE t.relative_of_id = :id AND r.as = 'SIBLING'",
            nativeQuery = true)
    public List<Relative> getSiblings(Integer id);
    public Optional <Relative> findById(Integer id);

}