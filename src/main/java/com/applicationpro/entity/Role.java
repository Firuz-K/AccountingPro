package com.applicationpro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends BaseEntity{

    private String description;

//    @OneToOne(mappedBy = "role")
//    private User user;

}
