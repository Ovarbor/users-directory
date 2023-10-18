package com.example.mainservice.repo;
import com.example.mainservice.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    Integer countUserByImageId(long id);
}
