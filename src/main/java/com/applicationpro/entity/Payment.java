package com.applicationpro.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted=false")
@Table(name = "payments")
public class Payment extends BaseEntity{

    private String month;

    @Column(columnDefinition = "DATE")
    private LocalDate year;

    private Double amount;

    private Boolean isPaid;

    private String institutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;



}
