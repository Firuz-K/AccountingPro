package com.applicationpro.dto.common;

import com.applicationpro.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
public class TotalInvoiceDto {
    private InvoiceDTO invoiceDto;
    private Double totalAmount;
    private Double totalTax;
}
