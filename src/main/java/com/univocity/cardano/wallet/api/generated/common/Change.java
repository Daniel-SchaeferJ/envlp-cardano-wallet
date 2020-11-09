package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Change {


	@JsonProperty("address")
	private String address;

	@JsonProperty("amount")
	private Amount amount;

	@JsonProperty("derivation_path")
	private ArrayList<String> derivationPath;

	/**
	 * Returns the address.
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Defines the address.
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @param address the address
	 */
	public void setAddress(String address){
		if (address == null) {
			throw new IllegalArgumentException("Value of address cannot be null");
		}

		this.address = address;
	}

	/**
	 * Returns the amount.
	 * 
	 * @return the amount
	 */
	public Amount getAmount(){
		return amount;
	}

	/**
	 * Defines the amount.
	 * 
	 * @param amount the amount
	 */
	public void setAmount(Amount amount){
		if (amount == null) {
			throw new IllegalArgumentException("Value of amount cannot be null");
		}

		this.amount = amount;
	}

	/**
	 * Returns the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * Indexes without `H` suffix are called `Soft`.
	 * Indexes with `H` suffix are called `Hardened`.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @return the derivation path
	 */
	public ArrayList<String> getDerivationPath(){
		return derivationPath;
	}

	/**
	 * Defines the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * Indexes without `H` suffix are called `Soft`.
	 * Indexes with `H` suffix are called `Hardened`.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @param derivationPath the derivation path
	 */
	public void setDerivationPath(ArrayList<String> derivationPath){
		if (derivationPath == null) {
			throw new IllegalArgumentException("Value of derivationPath cannot be null");
		}

		this.derivationPath = derivationPath;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
