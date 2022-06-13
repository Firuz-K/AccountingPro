package com.applicationpro.entity;

import com.applicationpro.enums.CompanyType;
import com.applicationpro.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    private String zipCode;

    private String address;

    @Enumerated(EnumType.STRING)
    private State state;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User createdBy;
//
//    @Column(columnDefinition = "DATE")
//    private LocalDate createdTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User updatedBy;
//
//    @Column(columnDefinition = "DATE")
//    private LocalDate UpdatedTime;
//
//    private boolean isDeleted;
    // they exist in base entity


}
