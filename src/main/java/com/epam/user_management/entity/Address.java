package com.epam.user_management.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table(name="address")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer addressId;

    @Column
    private String address;
//    @Column
//    private String currentAddress;
//    @Column
//    private String permanentAddress;
//    @Column
//    private String workAddress;

    // HERE FOREIGN KEY COLUMN WILL COME
}
