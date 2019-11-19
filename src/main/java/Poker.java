import java.util.LinkedList;
import java.util.List;

public class Poker {

    private int[] cards;
    private static final int CARD_MAX = 13;
    private static final int CARD_MIN = 1;

    public Poker() {

    }

    public String getHand(int[] cards) throws Exception {
        if (!ValidateCards(cards)) {
            throw new Exception("Input not valid");
        }
        this.cards = cards;

        int[] solveArr = getCardCountArray();

        int max = getMaxCount(solveArr);

        switch (max) {
            case 1:
                return handleHighestCard(solveArr);
            case 2:
                return "";
            case 3:
                return handleThree(solveArr);
            case 4:
                return handleFourOfAKind(solveArr);
        }

        int[][] twoMostFreqCards = getTwoMostFrequentCards(solveArr);

        switch (twoMostFreqCards.length) {
            case 0:
                throw new RuntimeException("Invalid");
            case 1:
                return handleOutput(twoMostFreqCards[0], null);
            case 2:
                return handleTwoCards(twoMostFreqCards);
            case 3:
            default:
                return handleFourOfAKind(solveArr);
        }
    }

    private int getMaxCount(int[] solveArr) {
        int max = 0;

        for (int count : solveArr) {
            if (count > max) max = count;
        }

        return max;
    }

    private String handleHighestCard(int[] solveArr) {
        for (int i = solveArr.length - 1; i >= CARD_MIN; i--) {
            if (solveArr[i] > 0)
                return "Highest card: " + i;
        }

        throw new RuntimeException("Invalid");
    }

    private String handleFourOfAKind(int[] solveArr) {
        for (int i = solveArr.length - 1; i >= CARD_MIN; i--) {
            if (solveArr[i] == 4)
                return "Highest card: " + i;
        }

        throw new RuntimeException("Invalid");
    }

    private String handleThree(int[] solveArr) {
        int threeOfAKind = -1;
        int twoOfAKind = -1;

        for (int i = 0; i < solveArr.length; i++) {
            if (solveArr[i] == 3)
                threeOfAKind = i;
            else if (solveArr[i] == 2)
                twoOfAKind = i;
        }

        if (twoOfAKind > 0) {
            return "Full House of: 3 " + threeOfAKind + "'s and 2 " + twoOfAKind + "'s";
        } else
            return "Three of a kind of: " + threeOfAKind;
    }

    private String handleTwoCards(int[][] twoMostFreqCards) {
        int[] card1 = twoMostFreqCards[0];
        int[] card2 = twoMostFreqCards[1];

        if (card1[1] > card2[1]) {
            return handleOutput(card1, card2);
        } else {
            return handleOutput(card2, card1);
        }
    }

    // FULL HOUSE , POKER THREE TWO HIGHEST card
    private String handleOutput(int[] card1, int[] card2) {
        if (card1[1] == 4) {
            return "Poker of " + card1[0];
        }

        if (card2 == null) {
            int card_value = card1[0];

            switch (card1[1]) {
                case 3:
                    return "Three of a kind of " + card_value;
                case 2:
                    return "Two of a kind of " + card_value;
                case 1:
                    return "Highest card " + card_value;
                default:
                    throw new RuntimeException("Invalid Value" + card_value);
            }
        } else {
            if (card1[1] == 3)
                return "Full House of:" + card1[0] + " and " + card2[0];
            else
                return "Pair of: " + card1[0] + " and " + card2[0];
        }
    }

    private int[] getCardCountArray() {
        int[] solveArr = new int[CARD_MAX];

        for (int card : cards)
            solveArr[card]++;


        return solveArr;
    }

    private int[][] getTwoMostFrequentCards(int[] cardCountArr) {
        List<int[]> mostFreqCards = new LinkedList<>();

        for (int i = 0; i < cardCountArr.length; i++) {
            if (cardCountArr[i] > 0) {
                int[] importantCard = {i, cardCountArr[i]};

                mostFreqCards.add(importantCard);
            }
        }

        return mostFreqCards.toArray(new int[mostFreqCards.size()][2]);
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
