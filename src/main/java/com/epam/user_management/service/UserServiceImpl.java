package com.epam.user_management.service;


import com.epam.user_management.entity.Address;
import com.epam.user_management.entity.User;
import com.epam.user_management.entity.Role;
import com.epam.user_management.exception.UserException;
import com.epam.user_management.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void addData(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> showDataWithPaging(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteData(Integer action,Integer target) {

        User tempAction=userRepository.findById(action).orElseThrow(()-> new UserException(HttpStatus.NOT_FOUND,"User not found"));


        if(tempAction.getRoleList().stream().anyMatch(n->n.getRole().equalsIgnoreCase("admin"))){
            User tempTarget = userRepository.findById(target).orElseThrow(()->new UserException(HttpStatus.NOT_FOUND,"User not found"));


                userRepository.deleteById(target);
        }
        else{
            throw new UserException(HttpStatus.FORBIDDEN,"User Access Denied");
        }

    }


    @Override
    public void updateData(User user){
        userRepository.save(user);
    }

    @Override
    public List<Role> getRole(Integer empId) { // do not use throwable use user defined exception
        User temp=userRepository.findById(empId).orElseThrow(()-> {
                return new UserException(HttpStatus.NOT_FOUND,"User not found")  ;

        });
        return temp.getRoleList();
    }

    @Override
    public User getById(Integer empId) {
        return userRepository.findById(empId).orElseThrow(()->new UserException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @Override
    public List<User> sortData(String attribute, String order) {
        List<User>temp=userRepository.findAll();

        // you cannot use stream since when you do User::getSalary it may have some null values which sorted function cannot handle
//        System.out.println(temp.stream().sorted(Comparator.comparing(User::getSalary)).toList());
//        return temp.stream().sorted(Comparator.comparingInt(User::getSalary)).toList();


        Sort sort = Sort.by(Sort.Direction.fromString(order), attribute);
        return userRepository.findAll(sort);

    }

    @Override
    public List<User> sortByName() {
        Sort sort=Sort.by("userName");
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> getByName(String empName) {
        List<User>nameSearch = userRepository.findByName(empName);
        if(nameSearch.isEmpty()){
            throw new UserException(HttpStatus.NOT_FOUND,"No users found with given name");
        }
        return nameSearch;
    }

}
