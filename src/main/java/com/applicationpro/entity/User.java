package com.applicationpro.entity;

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

//@Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private  Company company;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

//    @Column(columnDefinition = "DATE")
//    private LocalDate created_time;
//    @Column(columnDefinition = "DATE")
//    private LocalDate updated_time;
    // they exist in base entity, omer


}

/**
 * 1) Company/name/feature
 * 2) Commit your branch
 * 3) Go to master branch and update (do not checkout)
 * 4) Merge into your local ( To deal with potential conflicts)
 * 5) You can push now
 */