package com.example.HR_System.repositories;

import com.example.HR_System.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);


    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Employee e WHERE e.name = :name AND e.address = :address")
    boolean existsByNameAndAddress(@Param("name") String name, @Param("address") String address);

    Optional<Employee> findByEmail(String email);

}
