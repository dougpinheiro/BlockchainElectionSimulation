package br.dougpinheiro.blockchainvotesample.entity;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.text.MaskFormatter;

import br.dougpinheiro.blockchainvotesample.utils.DataEncryption;

public class Blockchain {
	
	protected Map<Long, Block> transactions;
	
	protected final String SIGNATURE = "MySignature";
	
	public Blockchain() {
		this.transactions = new HashMap<Long, Block>(0);
		
		/*Genesis Block*/
		Block genesis;
		try {
			genesis = new Block(DataEncryption.encode(SIGNATURE), new Vote("test", null));
			this.transactions.put(new Long(0L), genesis);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public void showTransactions() {
		transactions.values().stream().forEach((c) -> {
			System.out.println(c.toString()+"\n");
		});
	}
	
	public Integer getTransactionsSize() {
		Integer i = this.transactions.size();
		return i;
	}
	
	public Block getTransactionsLastBlock() {
		Integer i = this.transactions.size()-1;
		return this.transactions.get(i.longValue());
	}
	
	public void addNewBlock(Block block) {
		long start = System.currentTimeMillis();
		if(block.isThisBlockValid(getTransactionsLastBlock(), block)) {
			if(!this.transactions.values().stream().filter((p)->{
				if(block.getVote().getEmissor() == p.getVote().getEmissor()){
					return true;
				}else {
					return false;
				}
			}).parallel().findAny().isPresent()) {
				Long position = getTransactionsSize().longValue();
				transactions.put(position, block);
				System.out.println("Block "+position+" added : \n\t"+block.toString());
			}else {
				System.out.println("Block NOT added - Emissor had voted already: \n\t"+block.toString());
			}
		}
		long finish = System.currentTimeMillis() - start;
		System.out.println("Time of block process: "+finish +" milliseconds.");
	}
	
	public String getPreviousHash() {
		Integer size = getTransactionsSize();
		return this.transactions.get(new Long(size-1)).getHash();
	}

	
	public void countVotes() {
		/*Count CANDIDATE_A*/
		long voteCount_A = this.transactions.values().stream().skip(1).filter((b) -> {
			if(b.getVote().getCandidates().name().equals(Candidates.CANDIDATE_A.name())) {
				return true;
			}else {
				return false;
			}
		}).count();
		
		System.out.println("CANDIDATE_A: "+voteCount_A);

		/*Count CANDIDATE_B*/
		long voteCount_B = this.transactions.values().stream().skip(1).filter((b) -> {
			if(b.getVote().getCandidates().name().equals(Candidates.CANDIDATE_B.name())) {
				return true;
			}else {
				return false;
			}
		}).count();
		
		System.out.println("CANDIDATE_B: "+voteCount_B);
		
	}

	public boolean isBlockchainSafe() {
		if(this.transactions.keySet().stream().skip(1).filter((k) -> {
			if(!this.transactions.get(k-1).getHash().equals(this.transactions.get(k).getPreviousHash())) {
				return true;
			}else {
				return false;
			}
		}).findAny().isPresent()) {
			return false;
		}
		return true;
	}	
	
	/*Test the blockchain*/
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		Blockchain bc = new Blockchain();
		
		/*Simulation*/
		Stream<Integer> votos = Stream.iterate(0, f -> f+1).limit(10);
		
		votos.forEach((p)->{
			Long id = Math.round((Math.random()*99999999999L));
						
			MaskFormatter mf;
			String emissor = "";
			try {
				mf = new MaskFormatter();
				mf.setValueContainsLiteralCharacters(false);
				mf.setMask("###.###.###-##");
				emissor = mf.valueToString(id);
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
						
			try {
				Long randomPosition = Math.round(Math.random()*2);
				Vote v = new Vote(emissor,Candidates.values()[randomPosition.intValue() == 1 ? 0 : 1]);
				String previousHash = bc.getPreviousHash();
				Block block = new Block(previousHash, v);
				bc.addNewBlock(block);				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		});
		
		//bc.showTransactions();
		
		bc.countVotes();
		
		System.out.println("Blockchain is safe: "+bc.isBlockchainSafe());
		
	}

}
