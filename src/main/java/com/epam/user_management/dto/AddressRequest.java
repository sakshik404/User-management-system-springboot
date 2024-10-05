package com.epam.user_management.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data   //if you forget this you are as good as dead //without setting value it is going to be null and will always throw notblank error
public class AddressRequest {


    @NotEmpty(message="Address cannot be empty ")
    private String address;


}
