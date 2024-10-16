import org.json.JSONArray;
import org.json.JSONObject;

public class MarketDataParser {
    
    // Method to extract the current price for a specific pair
    public static Double getCurrentPrice(String response, String pair) {
        try {
            JSONArray markets = new JSONArray(response);  // Parse the response into a JSON array

            for (int i = 0; i < markets.length(); i++) {
                JSONObject market = markets.getJSONObject(i);
                if (market.getString("symbol").equals(pair)) {
                    // Check for the 'last_price' key
                    if (market.has("last_price")) {
                        return market.getDouble("last_price");  // Return the current price
                    } else {
                        System.err.println("Key 'last_price' not found for pair: " + pair);
                        return null;  // Return null if the key is missing
                    }
                }
            }
            // If the market pair is not found, throw an informative exception
            throw new RuntimeException("Market pair not found: " + pair);
        } catch (Exception e) {
            // Handle any parsing exceptions
            System.err.println("Error parsing market data: " + e.getMessage());
            return null;  // Return null in case of an error
        }
    }
}