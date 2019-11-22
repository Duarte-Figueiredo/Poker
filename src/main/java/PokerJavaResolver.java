import java.util.*;
import java.util.stream.Collectors;

public class PokerJavaResolver {

    private static final int CARD_MAX = 13;
    private static final int CARD_MIN = 1;

    static String solve(List<Integer> cards) {
        List<Integer[]> cardCount = getCount(cards);

        switch (cardCount.size()) {
            case 5:
                return getHighestCard(cardCount);
            case 4:
                return getSimplePair(cardCount);
            case 3:
                return getTrioOrTwoPairs(cardCount);
            case 2:
                return getFullHouseOrFourOfAKind(cardCount);
            default:
                throw new RuntimeException("Invalid State");
        }
    }

    private static List<Integer[]> getCount(List<Integer> cards) {
        HashMap<Integer, Integer> cardHashMap = new HashMap<>();

        for (Integer card : cards) {
            int value = cardHashMap.getOrDefault(card, 0);

            cardHashMap.put(card, value + 1);
        }

        List<Integer[]> cardCount = new LinkedList<>();

        for (Integer card : cardHashMap.keySet()) {
            Integer[] cardPair = new Integer[2];
            cardPair[0] = card;
            cardPair[1] = cardHashMap.get(card);

            cardCount.add(cardPair);
        }

        return cardCount;
    }

    private static String getHighestCard(List<Integer[]> cardCount) {
        Optional<Integer[]> max = cardCount.stream()
                .max(Comparator.comparing(i -> i[0]));

        if (max.isPresent()) {
            return "Highest card :" + max.get()[0];
        }

        throw new RuntimeException("Invalid State");
    }

    private static String getSimplePair(List<Integer[]> cardCount) {
        Optional<Integer[]> max = cardCount.stream().max(Comparator.comparing(i -> i[1]));

        if (max.isPresent()) {
            int value = max.get()[0];

            return "Pair of :" + value;
        }

        throw new RuntimeException("Invalid State");
    }

    private static String getTrioOrTwoPairs(List<Integer[]> cardCount) {
        List<Integer[]> max = cardCount
                .stream()
                .sorted(Comparator.comparing(i -> i[1]))
                .filter(i -> i[1] > 1)
                .collect(Collectors.toList());

        int size = max.size();

        switch (size) {
            case 1:
                return "Trio of :" + max.get(0)[0];
            case 2:
                return "Pairs of :" + max.get(0)[0] + " and :" + max.get(1)[0];
            default:
                throw new RuntimeException("Invalid State");
        }
    }

    private static String getFullHouseOrFourOfAKind(List<Integer[]> cardCount) {
        List<Integer[]> max = cardCount
                .stream()
                .filter(i -> i[1] > 1)
                .sorted(Comparator.comparing(i -> i[1]))
                .collect(Collectors.toList());

        int size = max.size();

        switch (size) {
            case 1:
                return "Four of :" + max.get(0)[0];
            case 2:
                return "Full House of 3 :" + max.get(1)[0] + " and 2 :" + max.get(0)[0];
            default:
                throw new RuntimeException("Invalid State");
        }
    }

    private static boolean ValidateCards(int[] cards) {
        if (cards == null)
            return false;
        else if (cards.length != 5)
            return false;

        for (int card : cards) {
            if (card < CARD_MIN || card > CARD_MAX)
                return false;
        }

        return true;
    }
}
