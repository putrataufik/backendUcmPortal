package com.ucmportal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ucmportal.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
    List<User> findByNameContains(String name); //katanya ini fungsi untuk search nanti taro di form saja.
    User findByName(String name);

    //Kebutuhan Login
    List<User> findByNameContainsAndLevel(String name, String level);
    Optional<User> findOneByNameAndPassword(String name, String Password);

    User findByNameAndPassword(String name, String password);
}
