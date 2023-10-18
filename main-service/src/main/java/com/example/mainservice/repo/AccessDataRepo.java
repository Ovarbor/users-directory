package com.example.mainservice.repo;

import com.example.mainservice.model.AccessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessDataRepo extends JpaRepository<AccessData, Long> {

    @Query(value = "SELECT c.username FROM AccessData c")
    List<String> findUserByName();
}
