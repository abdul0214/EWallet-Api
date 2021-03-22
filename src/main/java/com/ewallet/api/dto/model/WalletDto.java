package com.ewallet.api.dto.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletDto extends BaseEntityDto {

	private String name;

	private String currency;

	private Double balance;

	private LocalDateTime created;

}