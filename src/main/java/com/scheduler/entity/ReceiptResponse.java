package com.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponse {

	private String firstName;
    private String lastName;
    private long pricePaid;
}
