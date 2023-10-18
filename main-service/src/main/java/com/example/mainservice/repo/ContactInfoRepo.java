package com.example.mainservice.repo;

import com.example.mainservice.model.ContactInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends PagingAndSortingRepository<ContactInfo, Long> {

    Integer countContactInfoByUserId(long id);
}
