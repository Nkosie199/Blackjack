import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTests {
    @Test
    public void Billy() {
    	Map<String, Boolean> dealersHand = new LinkedHashMap<>(); //19
    	dealersHand.put("JS", true);
    	dealersHand.put("9H", true);
    	Map<String, Boolean> billysHand = new LinkedHashMap<>(); //15
    	billysHand.put("2S", true);
    	billysHand.put("2D", true);
    	billysHand.put("2H", true);
    	billysHand.put("4D", true);
    	billysHand.put("5C", true);
        String expectedResult = "beats dealer";
        String actualResult = Blackjack.result(dealersHand, billysHand);
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void Lemmy() {
    	Map<String, Boolean> dealersHand = new LinkedHashMap<>(); //19
    	dealersHand.put("JS", true);
    	dealersHand.put("9H", true);
    	Map<String, Boolean> lemmysHand = new LinkedHashMap<>(); //8 (ace1 = 1, ace2 = 1); 19 (ace = 1, ace2 = 11)
    	lemmysHand.put("1S", true);
    	lemmysHand.put("7H", true);
    	lemmysHand.put("1D", true);
        String expectedResult = "draw";
        String actualResult = Blackjack.result(dealersHand, lemmysHand);
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void Andrew() {
    	Map<String, Boolean> dealersHand = new LinkedHashMap<>(); //19
    	dealersHand.put("JS", true);
    	dealersHand.put("9H", true);
    	Map<String, Boolean> andrewsHand = new LinkedHashMap<>(); //18
    	andrewsHand.put("KD", true);
    	andrewsHand.put("4S", true);
    	andrewsHand.put("4C", true);
        String expectedResult = "loses";
        String actualResult = Blackjack.result(dealersHand, andrewsHand);
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void Carla() {
    	Map<String, Boolean> dealersHand = new LinkedHashMap<>(); //19
    	dealersHand.put("JS", true);
    	dealersHand.put("9H", true);
    	Map<String, Boolean> carlasHand = new LinkedHashMap<>(); //25
    	carlasHand.put("QC", true);
    	carlasHand.put("6S", true);
    	carlasHand.put("9D", true);
        String expectedResult = "loses";
        String actualResult = Blackjack.result(dealersHand, carlasHand);
        assertEquals(expectedResult, actualResult);
    }
}
