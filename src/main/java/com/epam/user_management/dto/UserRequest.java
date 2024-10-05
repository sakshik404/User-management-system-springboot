package com.epam.user_management.dto;

import com.epam.user_management.entity.Address;
import com.epam.user_management.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest{

    @NotBlank(message="EMPLOYEE NAME REQUIRED")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)?$",message = "Must contain letters only")  // ? = makes the second string after space as optional , $= marks the end of the string ,ensures nothing should come after the strin
    private String userName;

    @NotNull(message="EMPLOYEE salary REQUIRED")
    @Min(value=100,message = "salary should be greater than zero")
    private Integer salary;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message="AddressList cannot be empty ")
    @Valid
    private List<AddressRequest>addressList;


    @NotEmpty(message="RoleList cannot be empty")
    @Valid
    private List<RoleRequest>roleList;

//    public void processAddress(){   //requires calling the method , hence call the addressdto
//        for(Address address : addressList){
//
//            @NotBlank(message="Address is mandatory")
//            String str=address.getAddress();
//        }
//    }


}
