package com.epam.user_management;

import com.epam.user_management.entity.Address;
import com.epam.user_management.entity.User;
import com.epam.user_management.entity.Role;
import com.epam.user_management.repository.Repository;
import com.epam.user_management.service.UserService;
import com.epam.user_management.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserManagementApplicationTests  {

	@Mock
	Repository repositoryMock;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	void UserServiceImplTest() throws Throwable {
	User emp=new User();
	// since i have not defined in user class @Allargsconstructor instead defined @Data
	emp.setUserId(1);
	emp.setUserName("sakshi");
	emp.setAddressList(Arrays.asList(new Address(1,"Virar")));
	emp.setRoleList(Arrays.asList(new Role(1,"Hr")));

	when(repositoryMock.findById(1)).thenReturn(Optional.of(emp));
	assertEquals(userServiceImpl.getById(1),emp);
	// assertEquals()

	}

}
