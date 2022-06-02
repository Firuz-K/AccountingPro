package com.applicationpro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Where(clause = "is_deleted=false")
public class Role {

    @Id
    private Long id;
    private String name;
    private boolean enabled;
}
