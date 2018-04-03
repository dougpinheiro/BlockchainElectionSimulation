package br.dougpinheiro.blockchainvotesample.entity;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Vote extends Transaction{

	private Candidates candidate;
	
	public Vote(String emissor, Candidates candidate) throws NoSuchAlgorithmException {
		super(emissor);
		this.candidate = candidate;
	}
	
	public Candidates getCandidates() {
		return candidate;
	}

	@Override
	public String toString() {
		return "Vote [candidate=" + candidate + "emissor=" + super.getEmissor() + "time="+new Date(super.getTime()) + "]";
	}	

}
