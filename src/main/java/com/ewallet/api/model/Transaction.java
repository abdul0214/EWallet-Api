package com.ewallet.api.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(schema = "public", name = "transactions")
public class Transaction extends BaseEntity {

	@Builder.Default
	private LocalDateTime time = LocalDateTime.now();

	private Double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;
}
