package com.applicationpro.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
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

    private Company company;         //make sure this is long and  not company or list of companies.

    private CompanyType type;

    private String zipCode;

    private String address;

    private State state;

    boolean enabled;

    private User createdBy;           //double check.

    private LocalTime createdTime;

    private User updatedBy;           //double check

    private LocalTime UpdatedTime;

    private boolean isDeleted;


}
