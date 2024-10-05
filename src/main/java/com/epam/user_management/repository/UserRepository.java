package com.epam.user_management.repository;

import com.epam.user_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
        @Query("Select u from User u where u.userName = :name")
        public List<User> findByName(String name);

//        @Query("Select u from User u where u.roleList.contains =:role")
//        public List<User>findByRole(String role);


}
