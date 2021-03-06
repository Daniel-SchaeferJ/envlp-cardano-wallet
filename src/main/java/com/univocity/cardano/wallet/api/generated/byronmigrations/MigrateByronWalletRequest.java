package com.univocity.cardano.wallet.api.generated.byronmigrations;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#migrateByronWallet(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MigrateByronWalletRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("addresses")
	private ArrayList<String> addresses;

	/**
	 * Returns the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the wallet's master passphrase.
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase the wallet's master passphrase.
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the recipient addresses.
	 * - Format: {@code base58|bech32}.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @return the recipient addresses.
	 */
	public ArrayList<String> getAddresses(){
		return addresses;
	}

	/**
	 * Defines the recipient addresses.
	 * - Format: {@code base58|bech32}.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @param addresses the recipient addresses.
	 */
	public void setAddresses(ArrayList<String> addresses){
		if (addresses == null) {
			throw new IllegalArgumentException("Value of addresses cannot be null");
		}

		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
