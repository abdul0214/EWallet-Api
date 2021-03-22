package com.ewallet.api.dto.model;

import com.ewallet.api.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WalletAssembler {


	public List<WalletDto> toModel(List<Wallet> entities) {
		if (entities == null) {
			return null;
		}

		List<WalletDto> list = new ArrayList<WalletDto>(entities.size());
		for (Wallet wallet : entities) {
			list.add(toModel(wallet));
		}

		return list;
	}

	public WalletDto toModel(Wallet entity) {
		WalletDto dto = new WalletDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBalance(entity.getBalance());
		dto.setCurrency(entity.getCurrency().toString());
		dto.setCreated(entity.getCreated());
		return dto;
	}
}
