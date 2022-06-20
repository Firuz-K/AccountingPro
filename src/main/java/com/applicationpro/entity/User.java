package com.applicationpro.entity;

import com.applicationpro.enums.ProductStatus;
import com.applicationpro.enums.UserStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

//@AllArgConstructor, why we cannot put it? Ask Baha

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String phone;

//    @Enumerated(EnumType.STRING)
//    private UserStatus userStatus;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}

