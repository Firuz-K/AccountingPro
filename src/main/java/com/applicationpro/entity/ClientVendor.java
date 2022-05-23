package com.applicationpro.entity;

import com.applicationpro.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;
                                // To-do: Annotations for each field.

@Entity
@Table(name = "client_vendor")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class ClientVendor extends BaseEntity {

    private String companyName;

    private String phone;

    private String email;

    @ManyToMany
    private Company company;                 //make sure this is long and  not company or list of companies.

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    private String zipCode;

    private String address;

    @Enumerated(EnumType.STRING)
    private State state;                   //To-do notes left in the State.java file

    boolean enabled;

    @ManyToMany
    private List<User> createdBy;           //double check.

    private LocalTime createdTime;

    @ManyToMany
    private List<User> updatedBy;           //double check (1. is many-to-many correct?) (list or set)(fetch type)

    private LocalTime UpdatedTime;

    private boolean isDeleted;


}
