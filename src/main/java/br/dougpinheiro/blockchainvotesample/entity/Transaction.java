package br.dougpinheiro.blockchainvotesample.entity;

import br.dougpinheiro.blockchainvotesample.utils.DataEncryption;

public class Transaction {

	private long time;
	private String emissor;

	public Transaction(String emissor) {
		this.time = System.currentTimeMillis();
		this.emissor = DataEncryption.encode(emissor);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	@Override
	public String toString() {
		return "Transaction [time=" + time + ", emissor=" + emissor + "]";
	}
	
}
