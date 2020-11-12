package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.*;

public class CoinSelection extends Wrapper<AbstractCoinSelectionResponse> {
	public CoinSelection(AbstractCoinSelectionResponse original, WalletApi api) {
		super(original, api);
	}

	static ArrayList<Payment> toPaymentList(Map<String, BigDecimal> payments) {
		ArrayList<Payment> out = new ArrayList<>();
		payments.forEach((address, adaAmount) -> {
			Payment payment = new Payment();
			payment.setAddress(address);
			Amount amount = new Amount();
			amount.setUnit("lovelace");
			amount.setQuantity(adaToLovelace(adaAmount));
			payment.setAmount(amount);
			out.add(payment);
		});
		return out;
	}
}