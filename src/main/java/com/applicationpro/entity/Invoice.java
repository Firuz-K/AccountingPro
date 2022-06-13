package com.applicationpro.entity;

import com.applicationpro.enums.InvoiceStatus;
import com.applicationpro.enums.InvoiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity{

    private String invoiceNumber;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;

    @ManyToOne
    @JoinColumn(name = "clientvendor_id")
    ClientVendor clientVendor;

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;
}

