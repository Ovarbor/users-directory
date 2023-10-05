package com.example.testapi.repo;
import com.example.testapi.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    Integer countUserByImageId(long id);
}
