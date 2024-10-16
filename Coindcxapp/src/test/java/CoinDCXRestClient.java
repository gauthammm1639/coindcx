import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinDCXRestClient {
    private static final String API_URL = "https://api.coindcx.com/exchange/v1/markets_details";

    // Method to fetch market data
    public static String getMarketData() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");  // Set GET request

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {  // Success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new RuntimeException("Failed to fetch market data: HTTP error code: " + responseCode);
        }
    }
}