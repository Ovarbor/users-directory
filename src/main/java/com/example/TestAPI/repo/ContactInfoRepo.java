package com.example.TestAPI.repo;
import com.example.TestAPI.model.ContactInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends PagingAndSortingRepository<ContactInfo, Long> {

}
