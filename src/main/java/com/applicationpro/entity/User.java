package com.applicationpro.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Table(name = "user")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private  Company company;
    //private Long company_id;

    private  Long created_by;

    private  Long updated_by;

    //private  Long role_id;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(columnDefinition = "DATE")
    private LocalDate created_time;
    @Column(columnDefinition = "DATE")
    private LocalDate updated_time;



}
