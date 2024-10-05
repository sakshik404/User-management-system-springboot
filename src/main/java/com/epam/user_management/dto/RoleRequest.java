package com.epam.user_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data   //if you forget this you are as good as dead //without setting value it is going to be null and will always throw notblank error
public class RoleRequest {
    @NotBlank(message ="Cannot be null or blank")
    @Column
    String role;
}
