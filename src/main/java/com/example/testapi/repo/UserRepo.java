package com.example.TestAPI.repo;
import com.example.TestAPI.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    Integer countUserByImageId(long id);
}
