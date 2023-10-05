package com.example.testapi.repo;
import com.example.testapi.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends PagingAndSortingRepository<Image, Long> {

}
