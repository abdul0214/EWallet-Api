package com.ewallet.api.rest;


import com.ewallet.api.dto.model.WalletDto;
import com.ewallet.api.model.*;
import com.ewallet.api.service.WalletService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Wallet")
@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://172.20.208.1:3000", "http://localhost:3000"})
public class WalletRest {

	private final WalletService walletService;

	@ApiOperation(value = "Get Wallet by Wallet id")
	@GetMapping("/{id}")
	public WalletDto getWallet(@PathVariable("id") Long id) {
		return walletService.getWallet(id);
	}

	@ApiOperation(value = "Create a Wallet for a Customer by Customer id")
	@PostMapping("/create")
	public WalletDto create(@RequestParam(name = "id", defaultValue = "1L") Long id, @RequestParam Currency currency, @RequestParam String name) {
		return walletService.create(id, currency, name);
	}

	@ApiOperation(value = "TopUp / Withdraw from a Wallet by Wallet id ")
	@PutMapping("/update/{id}")
	public WalletDto updateBalance(@PathVariable("id") Long id, @RequestParam TransactionType type, @RequestParam Double amount) {
		return walletService.updateBalance(id, amount, type);
	}

	@ApiOperation(value = "Get All Wallets")
	@GetMapping("/list")
	public List<WalletDto> getAll() {
		return walletService.getAll();
	}


}