import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
	private static Random random = new Random();
	private static Scanner sc = new Scanner(System.in);
	private static int noOfPlayers = 0;
	private static Player[] players;
	private static String[] cards = {
			"1C", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C",
			"1S", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S",
			"1D", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D",
			"1H", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H",
			"JC", "QC", "KC",
			"JS", "QS", "KS",
			"JD", "QD", "KD",
			"JH", "QH", "KH"
	};
	private static Map<String, Boolean> deck;
	private static int deckIndex = 0;
	private static int playerMax = 0;
    
    public static void main(String[] args) {
        System.out.println("*** Hello there! Welcome to Console Blackjack :-) ***");
        System.out.println("To start, please enter the number of players:");
        noOfPlayers = Integer.parseInt(sc.nextLine());
        players = new Player[noOfPlayers+1];
        players[0] = new Player("Dealer");
        for (int i=1; i<noOfPlayers+1; i++) {
        	System.out.println("Please enter the name of player "+i+":");
        	players[i] = new Player(sc.nextLine());
        }
        play();
    }
    
    public static void play() {
    	shuffleCards();
    	dealToPlayers();
    	printPlayers();
    	hitOrStand();
    }
    
    public static void hitOrStand() {
    	for (int l = 1; l < players.length; l++) {
    		Player player = players[l];
    		Map<String, Boolean> hand = player.getHand();
    		while (sumOfHand(hand) < 21) {
    			System.out.print(player.getName()+" ("+sumOfHand(hand)+"): ");
    			System.out.println("Hit (h) or Stand (s)?");
    			String choice = sc.nextLine();
    			if (choice.equals("h")) {
    				hand.put((String) deck.keySet().toArray()[deckIndex], true);
        			deckIndex++;
    			} else if (choice.equals("s")) {
    				break;
    			} else {
    				System.out.println("Invalid input, please enter either \"h\" or \"s\"");
    			}
    		}
    	}
    	/* uncomment the method call below if the dealer is allowed to hit to beat other players */
    	//dealerHits()
    	for (int l = 0; l < players.length; l++) {
    		Map<String, Boolean> dealersHand = players[0].getHand();
    		if (l > 0) {
    			Player player = players[l];
    			Map<String, Boolean> hand = player.getHand();
    			System.out.print(player.getName()+" --> "+sumOfHand(hand)+": ");
    			System.out.println(result(dealersHand, hand));
    		} else {
    			Player player = players[l];
    			Map<String, Boolean> hand = player.getHand();
    			System.out.println(player.getName()+" --> "+sumOfHand(hand));
    		}
    	}
    	
    }
    
    public static void dealerHits() {
    	for (int l = 0; l < players.length; l++) {
    		Player player = players[l];
    		Map<String, Boolean> hand = player.getHand();
    		int sum = sumOfHand(hand);
    		if ((playerMax < sum) && (sum < 21)) {
    			playerMax = sum;
    		}
    	}
    	Map<String, Boolean> dealerHand = players[0].getHand();
    	while (sumOfHand(dealerHand) < playerMax) {
    		dealerHand.put((String) deck.keySet().toArray()[deckIndex], true);
			deckIndex++;
    	}
    }
    
    public static int sumOfHand(Map<String, Boolean> hand) {
    	int sum = 0;
    	for (String card: hand.keySet()) {
    		String s = card.substring(0, card.length()-1);
    		int i;
    		if (s.equals("K") || s.equals("Q") || s.equals("J")) {
    			i = 10;
    			sum = sum + i;
    		} else if (s.equals("1")) {
    			if (sum + 11 > 21) {
    				i = 1;
        			sum = sum + i;
    			} else {
    				i = 11;
        			sum = sum + i;
    			}
    		} else {
    			i = Integer.parseInt(s);
    			sum = sum + i;
    		}
    	}
    	return sum;
    }
    
    public static void dealToPlayers() {
    	System.out.println("Dealing to players...");
    	for (int k = 0; k < players.length; k++) {
    		Player player = players[k];
    		Map<String, Boolean> hand = player.getHand();
    		if (player.getName().equals("Dealer")) {
    			hand.put((String) deck.keySet().toArray()[deckIndex], true);
    			deckIndex++;
    			hand.put((String) deck.keySet().toArray()[deckIndex], false);
    			deckIndex++;
    		} else {
    			hand.put((String) deck.keySet().toArray()[deckIndex], true);
    			deckIndex++;
    			hand.put((String) deck.keySet().toArray()[deckIndex], true);
    			deckIndex++;
    		}
    	}
    }
    
    public static void shuffleCards() {
    	System.out.println("Shuffling cards...");
        for (int i = 0; i < cards.length; i++) {
	        int r = i + random.nextInt(52 - i);
	        String temp = cards[r]; 
	        cards[r] = cards[i]; 
	        cards[i] = temp;
        }
        deck = new LinkedHashMap<>(52);
    	for (int j=0; j<cards.length; j++) {
			deck.put(cards[j], false);
    	}
        deckIndex = 0;
    }
    
    public static void printPlayers() {
    	System.out.println("Printing players and their hands...");
    	for (int j=0; j<noOfPlayers+1; j++) {
    		System.out.print("Player "+j+": "+players[j].getName()+". ");
    		System.out.print("Hand: ");
    		Map<String, Boolean> hand = players[j].getHand();
    		for (int k=0; k<hand.size(); k++) {
    			String key = (String) hand.keySet().toArray()[k];
    			boolean index = hand.get(key);
    			if (index) {
    				System.out.print(key+" ");
    			} else {
    				System.out.print("?? ");
    			}
    		}
    		System.out.println("");
    	}
    }
    
    public static void debugPrintCards() {
    	System.out.println("Debug printing all cards...");
    	for (String s: cards) {
    		System.out.print(s+" ");
    	}
    	System.out.println("\nDebug printing the deck...");
    	for (String d: deck.keySet()) {
    		System.out.print(d+" ");
    	}
    	System.out.println("");
    }
    
    public static String result(Map<String, Boolean> dealersHand, Map<String, Boolean> customersHand){
    	int dealer = sumOfHand(dealersHand);
    	int customer = sumOfHand(customersHand);
    	if ((dealer > 21) || ((customer > dealer) && (customer <= 21)) || (customersHand.size() >= 5 && (customer <= 21))) {
    		return "beats dealer";
    	} else if ((customer == dealer) && (customer <= 21)){
            return "draw";
        } else {
            return "loses";
        }
    }
}

class Player {
	private String name;
	private Map<String, Boolean> hand = new LinkedHashMap<>();
	
	public Player(String n) {
		this.setName(n);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Boolean> getHand() {
		return hand;
	}
}
