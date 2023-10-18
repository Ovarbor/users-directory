package com.example.mainservice.repo;

import com.example.mainservice.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends PagingAndSortingRepository<Image, Long> {

}
