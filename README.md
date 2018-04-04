# Blockchain Election Simulation

This is a simulation of an election using blockchain mechanism to grant that all data is not corrupted and it was not changed along the entire process.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java 8 (Minimum version required)
Maven (I used Maven but you can configure to the especific libraries)
Springboot (Here I started the project with springboot configuration because I will develop a web interface soon - It is not necessary at all)
```

### Running

There is nothing to install. To run, just run the main method at the Blockchain class. 

```
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
```

Below we have a sample of the result to an example election with just 10 voters that try to count votes for 2 candidates.  

```
Block 1 added : 
	Block [previousHash=$2a$10$wQh5WxAp8XAbz3kzx4vgyejQwqKKlPYmX4wDuVkQlQNc/LzBzOe7e, hash=$2a$10$1Vr8OTTLYucsAyvcW4Xk3upAq0nzhdIffeu7QgE2jKXK.JDQ8npCi, vote=Vote [candidate=CANDIDATE_Aemissor=$2a$10$Wcnn5RyEMKtUnIskNYqQveaTU/..nxZU8hdQgYlbiJ6/DEjgJlKrWtime=Tue Apr 03 10:04:46 GMT-03:00 2018]]
Time of block process: 7 milliseconds.
Block 2 added : 
	Block [previousHash=$2a$10$1Vr8OTTLYucsAyvcW4Xk3upAq0nzhdIffeu7QgE2jKXK.JDQ8npCi, hash=$2a$10$us8gV7e6h803fEhtkR.HourKKM1gUgEnzyD7vTlY8fNN1GCcw.6hm, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$3PJ81sx6eeCfmMvYSx22d.lok/D0IVLNaiMdA97uO49MRAKygdlmitime=Tue Apr 03 10:04:46 GMT-03:00 2018]]
Time of block process: 2 milliseconds.
Block 3 added : 
	Block [previousHash=$2a$10$us8gV7e6h803fEhtkR.HourKKM1gUgEnzyD7vTlY8fNN1GCcw.6hm, hash=$2a$10$xIEezN9PQygoIvrF9kKOHuv7xksCav39swJ7HAQV/InU0oiRO4G0W, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$tLLlxWzzM/0c4WwMVTkNVOGJwaKqMpbv3z/tNpHq0s/nvnAshiETGtime=Tue Apr 03 10:04:46 GMT-03:00 2018]]
Time of block process: 1 milliseconds.
Block 4 added : 
	Block [previousHash=$2a$10$xIEezN9PQygoIvrF9kKOHuv7xksCav39swJ7HAQV/InU0oiRO4G0W, hash=$2a$10$UzSMXrQWA.qsW2wfnFZpteSLsvzOUpYi7KO0iLdYFBSxVUnaSE3uq, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$QGqweqFeck/z962wERAj7eytvyWSIBFVUgV9UeHfeT9XifbnG5NE2time=Tue Apr 03 10:04:47 GMT-03:00 2018]]
Time of block process: 12 milliseconds.
Block 5 added : 
	Block [previousHash=$2a$10$UzSMXrQWA.qsW2wfnFZpteSLsvzOUpYi7KO0iLdYFBSxVUnaSE3uq, hash=$2a$10$LpQ01RGoCylLg/A.tKBSbuFAhvvsbRpYTVo4FBnGtLbN5sc6Krpqy, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$RNAxBImBtFFSg8Niqa.v7upgbmUWRn9uogjbcKBsJIa87PqQcDXmmtime=Tue Apr 03 10:04:47 GMT-03:00 2018]]
Time of block process: 1 milliseconds.
Block 6 added : 
	Block [previousHash=$2a$10$LpQ01RGoCylLg/A.tKBSbuFAhvvsbRpYTVo4FBnGtLbN5sc6Krpqy, hash=$2a$10$u7uX5V8FjOXdeHWBqdx9YeHA8SokZGbxmfkWq37OMSsPriKlitafq, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$iMQwL0N8nrJaVPFL36Fsze4KxcImvbitcj13MCSTTvHCPzGWx8ueGtime=Tue Apr 03 10:04:47 GMT-03:00 2018]]
Time of block process: 2 milliseconds.
Block 7 added : 
	Block [previousHash=$2a$10$u7uX5V8FjOXdeHWBqdx9YeHA8SokZGbxmfkWq37OMSsPriKlitafq, hash=$2a$10$xXiKs2keLbWCcCCZSZ64MO2o.Z8ate.X5JPhFnARkG.piniILkzt2, vote=Vote [candidate=CANDIDATE_Aemissor=$2a$10$fQvdh0kshs8Ik.1TJz1X6utaKkOiF5jvQx0S3aeDT3lM5Q8YA44COtime=Tue Apr 03 10:04:47 GMT-03:00 2018]]
Time of block process: 1 milliseconds.
Block 8 added : 
	Block [previousHash=$2a$10$xXiKs2keLbWCcCCZSZ64MO2o.Z8ate.X5JPhFnARkG.piniILkzt2, hash=$2a$10$8W7yGgZm/MyalZGaL/Lj4ezmI.xZV1BM4.52GCpNSX0/1pcC5UKbW, vote=Vote [candidate=CANDIDATE_Aemissor=$2a$10$sWnt8f4ERSN5KFDj7gi.a.NxgK6n4s7yORETiDvIzO5jJVmNXHsr6time=Tue Apr 03 10:04:47 GMT-03:00 2018]]
Time of block process: 12 milliseconds.
Block 9 added : 
	Block [previousHash=$2a$10$8W7yGgZm/MyalZGaL/Lj4ezmI.xZV1BM4.52GCpNSX0/1pcC5UKbW, hash=$2a$10$P1/ZuuHy5bX0ZwL3ygzDneQPPO6F4XeFDiAQ96Uz0z0ZB5EAZEFti, vote=Vote [candidate=CANDIDATE_Bemissor=$2a$10$YsnR6b/cFe1XYlZN3BPdn..3GndUWwo5u6dbthq/7wlrJ2wDY17Letime=Tue Apr 03 10:04:48 GMT-03:00 2018]]
Time of block process: 2 milliseconds.
Block 10 added : 
	Block [previousHash=$2a$10$P1/ZuuHy5bX0ZwL3ygzDneQPPO6F4XeFDiAQ96Uz0z0ZB5EAZEFti, hash=$2a$10$o98NznKppuKj4FLml5X1HuTKeB4eqSaezNPyWa35Fcop/gTPgm11m, vote=Vote [candidate=CANDIDATE_Aemissor=$2a$10$VMkU9G4eFLmEDVGJIDGPIumQZfmUVGo6114SZq9xpCYkuw9W3gA4.time=Tue Apr 03 10:04:48 GMT-03:00 2018]]
Time of block process: 0 milliseconds.
CANDIDATE_A: 4
CANDIDATE_B: 6
Blockchain is safe: true
```
## Commenting the code

In this simulation we can choose how many hypothetical voters we would have. In this case, we chose 10.

```
/*Simulation*/
Stream<Integer> votos = Stream.iterate(0, f -> f+1).limit(10);
```

Here we determine an exclusive number to identify each voter. In real life it would be a hash of figerprints or a number that identify the voter to the system. Indeed, the objective of the system at this moment is get unique information about the voter, but they will cannot be recognized after the process. All voters ids will be encrypted. 

```
votos.forEach((p)->{
	Long id = Math.round((Math.random()*99999999999L));
```

It is important to say that in this case just two candidates are running to win the election. So, we need to choose between the two options, CANDIDATE_A or CANDIDATE_B. Every vote will be created passing as parameter the emissor (voter) data and the candidate choice.

```
Long randomPosition = Math.round(Math.random()*2);
Vote v = new Vote(emissor,Candidates.values()[randomPosition.intValue() == 1 ? 0 : 1]);
```

After that, the block is created using the previous block hash and the vote that extends a Transaction object where the timestamp and the emissor have your date associated. 
Ahead it will be demonstrated how it happens if it is the first block. 

```
String previousHash = bc.getPreviousHash();
Block block = new Block(previousHash, v);
bc.addNewBlock(block);
```

In case of the first block, we have a signature that will represents the block 0 as above. We call the first block as Genesis Block and it is hardcoded.

```
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
```

Finally, after the process has finished, we can count the votes and also test if the Blockchain is trust.

```
bc.countVotes();
System.out.println("Blockchain is safe: "+bc.isBlockchainSafe());
```

## Built With

* [Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - The language used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Springboot](https://projects.spring.io/spring-boot/) - At this moment used just to encrypt the data.

## Versioning

We use [Maven](https://maven.apache.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/dougpinheiro/BlockchainElectionSample/tags). 

## Author

* **[Douglas Pinheiro](https://about.me/dougpinheiro)** 


