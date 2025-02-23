import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayAllCards() {
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class CardCollectionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Sample card entries
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");
        collection.addCard("Clubs", "10");
        collection.addCard("Spades", "Ace");
        
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Search cards by symbol");
            System.out.println("2. Display all cards");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    List<String> cards = collection.getCardsBySymbol(symbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for " + symbol);
                    } else {
                        System.out.println("Cards in " + symbol + ": " + cards);
                    }
                    break;
                case 2:
                    collection.displayAllCards();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
