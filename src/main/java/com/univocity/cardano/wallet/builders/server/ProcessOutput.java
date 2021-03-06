package com.univocity.cardano.wallet.builders.server;

import java.util.function.*;

public interface ProcessOutput<T> {

	T consumeOutput(Consumer<String> outputConsumer);

	T ignoreOutput();

}
