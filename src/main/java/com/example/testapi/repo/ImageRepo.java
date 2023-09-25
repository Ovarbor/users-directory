package com.example.TestAPI.repo;
import com.example.TestAPI.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends PagingAndSortingRepository<Image, Long> {

}
