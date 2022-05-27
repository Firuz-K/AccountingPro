package com.applicationpro.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{

    private String month;
    @Column(columnDefinition = "DATE")
    private LocalDate year;

    private Integer amount;
    private Integer isPaid;
    private String institutionId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Company company;


}
