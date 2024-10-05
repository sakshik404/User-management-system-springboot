package com.epam.user_management.controller;

import com.epam.user_management.dto.UserRequest;
import com.epam.user_management.entity.User;
import com.epam.user_management.entity.Role;
import com.epam.user_management.repository.Repository;
import com.epam.user_management.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
///////////////////////////// ORIGINAL //////////////////////////////////////////
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRequest userRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public void addData(@Valid @RequestBody UserRequest userRequest){
        // NOTE: make sure when you are adding any extra column to User and it is not being handled by UserRequest
        // then while mapping like below that column/attribute will get null value .
        // But obvious reason u are doing mapping from request to enity, if any of it is not validated that data is not added to table
        User user= this.objectMapper.convertValue(userRequest,User.class);
        userService.addData(user);
    }

    @GetMapping
    public Page<User> showDataWithPaging(@RequestParam(value="page")int page,
                                   @RequestParam(value="size")int size){
        Pageable pageable= PageRequest.of(page,size);
        return userService.showDataWithPaging(pageable);
    }

    @PutMapping
    public void updateData(@Valid @RequestBody UserRequest userRequest) throws Throwable {
        User user= this.objectMapper.convertValue(userRequest,User.class);
        userService.updateData(user);
    }

    @DeleteMapping("{action}/{target}")
    public void deleteData(@PathVariable Integer action,@PathVariable Integer target){
        userService.deleteData(action,target);
    }

    @GetMapping("sort/name")
    public List<User> sortByName(){
        return userService.sortByName();
    }

    @GetMapping("sort")
    public List<User>sortData(@RequestParam(value="sortby",required = true)String attribute,
                                    @RequestParam(value="order",required = false, defaultValue ="asc" )String order){

        return userService.sortData(attribute,order);
    }



    @GetMapping("/role/{empId}")
    public List<Role> getRole(@PathVariable Integer empId) throws Throwable {
        return userService.getRole(empId);
    }

    @GetMapping("id/{empId}")
    public User getById(@PathVariable Integer empId){
        return userService.getById(empId);
    }
    @GetMapping("name/{empName}")
    public List<User> getByName(@PathVariable String empName){
        return userService.getByName(empName);
    }



//    @GetMapping("/role")
//    public List<User> getByRole(@RequestParam(value="role")String role){
//        return respository.findByRoleList(role);
//    }


//    @GetMapping("/")
//    public User getUser(){
//
//    }
}
