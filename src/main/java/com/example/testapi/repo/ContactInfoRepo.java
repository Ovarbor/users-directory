package com.example.testapi.repo;
import com.example.testapi.model.ContactInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends PagingAndSortingRepository<ContactInfo, Long> {

    Integer countContactInfoByUserId(long id);
}
