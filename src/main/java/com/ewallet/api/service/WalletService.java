package com.ewallet.api.service;

import com.ewallet.api.dto.model.*;
import com.ewallet.api.model.*;
import com.ewallet.api.repository.*;
import com.ewallet.api.service.exception.types.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

	private final WalletRepository walletRepository;
	private final WalletAssembler walletAssembler;
	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;

	private final Gson gson;

	public WalletDto create(Long id, Currency currency, String name) {
		Wallet wallet = new Wallet();
		wallet.setName(name);
		wallet.setCurrency(currency);
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id, Customer.class));
		wallet.setCustomer(customer);
		walletRepository.save(wallet);
		return walletAssembler.toModel(wallet);
	}

	public WalletDto getWallet(Long id) {
		return walletAssembler.toModel(walletRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id, Wallet.class)));
	}

	public List<WalletDto> getAll() {
		return walletAssembler.toModel(walletRepository.findAll());
	}

	public WalletDto updateBalance(Long id, Double amount, TransactionType type) {
		Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id, Wallet.class));
		Transaction transaction = new Transaction();
		transaction.setTransactionType(type);
		transaction.setWallet(wallet);
		transaction.setAmount(amount);
		if (type == TransactionType.CREDIT) {
			amount = Math.abs(amount);
		}
		else if (type == TransactionType.DEBIT) {
			amount = -1 * Math.abs(amount);
		}
		if (wallet.getBalance() + amount >= 0) {
			wallet.setBalance(wallet.getBalance() + amount);
		}
		else {
			throw new TransactionNotAllowed(id, "Insufficient Funds", Wallet.class);
		}
		walletRepository.save(wallet);
		transactionRepository.save(transaction);
		return walletAssembler.toModel(wallet);
	}

}
