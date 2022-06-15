package com.applicationpro.dto;

import com.applicationpro.enums.InvoiceStatus;
import com.applicationpro.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class InvoiceDTO {
    private Long id;
    private String invoiceNumber;
    private InvoiceStatus invoiceStatus;
    private InvoiceType invoiceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

    private ClientVendorDTO clientVendor;
    private CompanyDTO company;
    private Boolean enabled;
}
