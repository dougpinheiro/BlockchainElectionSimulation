package br.dougpinheiro.blockchainvotesample.entity;

import br.dougpinheiro.blockchainvotesample.utils.DataEncryption;

public class Block {
	
	private String previousHash;
	
	private String hash;
	
	private Vote vote;
	
	public Block(String previousHash, Vote vote) {
		this.previousHash = previousHash;
		this.vote = vote;
		this.hash = doHash(this.toString());
	}

	public boolean isThisBlockValid(Block previous, Block selfBlock) {
		if(previous.getHash() == selfBlock.getPreviousHash()) {
			return true;
		}
		return false;
	}

	protected String doHash(String input) {
		return DataEncryption.encode(input);
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Block [previousHash=" + previousHash + ", hash=" + hash + ", vote=" + vote.toString() + "]";
	}
	
}
