package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.common.*;
import com.univocity.cardano.wallet.common.x509.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class WalletServer {

	public static EmbeddedWallet embedded() {
		return new WalletServerConfig("localhost");
	}

	public static RemoteWallet remote(String host) {
		return new WalletServerConfig(host);
	}

	public static class WalletServerConfig implements EmbeddedWallet, RemoteWallet, Node {

		boolean enableHttps = false;
		final String walletHost;
		int walletPort;
		int nodePort;
		File cardanoToolsDir;
		File blockchainDir;
		File nodeConfigurationFile;
		File nodeTopologyFile;
		Consumer<String> walletOutputConsumer;
		Consumer<String> nodeOutputConsumer;
		long testnetMagic;
		boolean isTestnet;
		boolean buildTemporaryBlockchain;

		class NodeServerBuilder implements NodeConfig, BlockchainConfig, TopologyConfig, PortConfig<WalletBuilder>, ProcessOutput<WalletBuilder>, WalletBuilder {
			NodeServerBuilder(long testnetMagicCode) {
				testnetMagic = testnetMagicCode;
			}

			@Override
			public TopologyConfig configuration(String pathToNodeConfiguration) {
				nodeConfigurationFile = toValidatedFile(pathToNodeConfiguration, false);
				return this;
			}


			@Override
			public BlockchainConfig topology(String pathToTopologyFile) {
				nodeTopologyFile = toValidatedFile(pathToTopologyFile, false);
				return this;
			}

			@Override
			public ProcessOutput<WalletBuilder> randomPort() {
				nodePort = Utils.randomPortNumber();
				return this;
			}

			@Override
			public ProcessOutput<WalletBuilder> port(int port) {
				nodePort = port;
				return this;
			}

			@Override
			public PortConfig storeBlockchainIn(String pathToBlockchain) {
				blockchainDir = toValidatedFile(pathToBlockchain, true);
				return this;
			}

			@Override
			public WalletBuilder consumeOutput(Consumer<String> outputConsumer) {
				nodeOutputConsumer = outputConsumer;
				return this;
			}

			@Override
			public WalletBuilder ignoreOutput() {
				return this;
			}

			@Override
			public WalletOptions wallet() {
				return new WalletServerBuilder();
			}
		}

		public class WalletServerBuilder implements WalletOptions, ProcessOutput<EmbeddedWalletServer> {
			@Override
			public ProcessOutput<EmbeddedWalletServer> port(int port) {
				walletPort = port;
				return this;
			}

			@Override
			public EmbeddedWalletServer consumeOutput(Consumer<String> outputConsumer) {
				walletOutputConsumer = outputConsumer;
				return new EmbeddedWalletServer(WalletServerConfig.this);
			}

			@Override
			public EmbeddedWalletServer ignoreOutput() {
				return new EmbeddedWalletServer(WalletServerConfig.this);
			}

			public PortConfig<EmbeddedWalletServer> enableHttps(){
				enableHttps = true;
				CertificateGenerator.getInstance().generate();
				return this;
			}

			@Override
			public ProcessOutput<EmbeddedWalletServer> randomPort() {
				walletPort = Utils.randomPortNumber();
				return this;
			}
		}

		public NodeConfig mainnetNode() {
			isTestnet = false;
			return new NodeServerBuilder(-1);
		}

		public NodeConfig testnetNode(long testnetMagicCode) {
			isTestnet = true;
			return new NodeServerBuilder(testnetMagicCode);
		}

		@Override
		public PortConfig<WalletBuilder> temporaryBlockchainNode() {
			isTestnet = true;
			buildTemporaryBlockchain = true;
			return new NodeServerBuilder(Math.abs(new Random().nextLong()));
		}

		public WalletServerConfig(String host) {
			this.walletHost = host;
		}

		@Override
		public RemoteWalletServer connectToPort(int walletPort) {
			this.walletPort = walletPort;
			return new RemoteWalletServer(this);
		}

		@Override
		public Node binariesIn(String pathToCardanoTools) {
			cardanoToolsDir = toValidatedFile(pathToCardanoTools, true);
			return this;
		}

		private static File toValidatedFile(String path, boolean isDir) {
			File file = new File(path);
			if (isDir) {
				if (!file.exists()) {
					file.mkdirs();
				}
			}
			if (!file.exists()) {
				throw new IllegalArgumentException((isDir ? "Directory " : "File ") + path + " does not exist");
			}
			return file;
		}
	}
}
