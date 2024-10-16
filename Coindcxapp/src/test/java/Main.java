import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        // User inputs currency pair and trigger price
        System.out.print("Enter the currency pair (e.g., BTCUSDT): ");
        String pair = scanner.nextLine().toUpperCase(); // Convert to uppercase to match API standards
        System.out.print("Enter trigger price: ");
        double triggerPrice = scanner.nextDouble();

        // Poll the API for market data every 10 seconds
        while (true) {
            try {
                // Fetch market data from the REST API
                String marketData = CoinDCXRestClient.getMarketData();
                System.out.println("Raw API Response: " + marketData); // Print raw response for debugging
                
                // Parse the current price for the given pair
                Double currentPrice = MarketDataParser.getCurrentPrice(marketData, pair);

                // Check if the current price was retrieved successfully
                if (currentPrice != null) {
                    System.out.println("Current price: " + currentPrice);

                    // Check if the trigger price is hit for buy or sell order
                    if (currentPrice <= triggerPrice) {
                        System.out.println("Trigger hit! Preparing buy order at price: " + currentPrice);
                        prepareBuyOrder(currentPrice);
                    } else if (currentPrice >= triggerPrice) {
                        System.out.println("Trigger hit! Preparing sell order at price: " + currentPrice);
                        prepareSellOrder(currentPrice);
                    }
                } else {
                    System.out.println("Current price not available. Retrying...");
                }

                // Wait 10 seconds before polling again
                Thread.sleep(10000);
            } catch (RuntimeException re) {
                // Handle runtime exceptions specifically for parsing issues
                System.err.println("Runtime Exception: " + re.getMessage());
                System.err.println("Current price not available. Retrying...");
            } catch (Exception e) {
                // Handle general exceptions
                System.err.println("Error occurred: " + e.getMessage());
            }
        }
    }

    // Method to simulate buy order
    public static void prepareBuyOrder(double price) {
        System.out.println("Simulating buy order at price: " + price);
        // Add logic for preparing buy payload here (if needed)
    }

    // Method to simulate sell order
    public static void prepareSellOrder(double price) {
        System.out.println("Simulating sell order at price: " + price);
        // Add logic for preparing sell payload here (if needed)
    }
}
