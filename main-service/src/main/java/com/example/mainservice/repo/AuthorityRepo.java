package com.example.mainservice.repo;

import com.example.mainservice.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {

}
