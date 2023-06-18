package com.ucmportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucmportal.entities.Form;
import com.ucmportal.entities.User;

@Repository
public interface FormRepo extends JpaRepository<Form, Long> {
    List<Form> findByTitleContainsIgnoreCaseAndStatus(String title, String status);
    List<Form> findByUser(User user);
    List<Form> findByTitle(String title);
    List<Form> findByStatusOrderByDateStartAsc(String status);
    List<Form> findByStatus(String status);
}
