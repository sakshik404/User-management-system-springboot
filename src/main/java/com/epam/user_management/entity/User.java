package com.epam.user_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.List;

@Table(name="user")
@Data
//@Setter
//@Getter
@Entity
@Check(constraints = "salary>0")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId; //used this name as table name is created with this name

    @Column
    private String userName;

    @Column
    private Integer salary;

    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_Id")  // foreign key for address
    List<Address> addressList;      // each user instance has more than 1 address entity
                                    // NOTE: this is instance relation and not a column

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name="roleId")
    )
    List<Role>roleList;
}
