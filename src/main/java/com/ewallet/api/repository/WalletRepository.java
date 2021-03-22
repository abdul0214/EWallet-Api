package com.ewallet.api.repository;

import com.ewallet.api.model.Wallet;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	@Query("select wallet from Wallet wallet where wallet.customer.id = :id order by wallet.balance desc , wallet.currency asc")
	List<Wallet> findAllforCustomer(@Param("id") Long id);

	@Query("select wallet from Wallet wallet order by wallet.id asc , wallet.balance desc")
	List<Wallet> findAll();


}
