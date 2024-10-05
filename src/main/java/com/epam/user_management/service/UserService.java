package com.epam.user_management.service;

import com.epam.user_management.entity.User;
import com.epam.user_management.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void addData(User user);

    void deleteData(Integer action,Integer target);

    void updateData(User user) throws Throwable;

    List<Role> getRole(Integer empId) throws Throwable;

    User getById(Integer empId);

    List<User> sortData(String attribute, String order);

    Page<User> showDataWithPaging(Pageable pageable);

    List<User> sortByName();

    List<User> getByName(String empName);
}
