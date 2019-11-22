import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class PokerJavaResolverTest {

    private List<Integer> cards;
    private String expectedContains;


    @Parameterized.Parameters
    public static Collection possibleCards() {
        return Arrays.asList(new Object[][]{
                {new LinkedList(Arrays.asList(1, 2, 3, 4, 5)), "Highest card :5"},
                {new LinkedList(Arrays.asList(5, 4, 3, 2, 1)), "Highest card :5"},
                {new LinkedList(Arrays.asList(4, 2, 5, 1, 3)), "Highest card :5"},
                {new LinkedList(Arrays.asList(4, 2, 13, 1, 3)), "Highest card :13"},
                {new LinkedList(Arrays.asList(4, 2, 5, 1, 4)), "Pair of :4"},
                {new LinkedList(Arrays.asList(4, 4, 5, 1, 3)), "Pair of :4"},
                {new LinkedList(Arrays.asList(4, 2, 5, 2, 4)), "Pairs of :2 and :4"},
                {new LinkedList(Arrays.asList(2, 2, 5, 4, 4)), "Pairs of :2 and :4"},
                {new LinkedList(Arrays.asList(4, 4, 5, 2, 2)), "Pairs of :2 and :4"},
                {new LinkedList(Arrays.asList(4, 2, 1, 2, 2)), "Trio of :2"},
                {new LinkedList(Arrays.asList(4, 2, 2, 2, 4)), "Full House of 3 :2 and 2 :4"},
                {new LinkedList(Arrays.asList(2, 2, 2, 2, 4)), "Four of :2"},
        });
    }

    public PokerJavaResolverTest(List<Integer> cards, String expectedContains) {
        this.cards = cards;
        this.expectedContains = expectedContains;
    }

    @Test
    public void getHand() {
        String actual = PokerJavaResolver.solve(cards);

        Assert.assertTrue("Input " + cards.toString() + " should be " + expectedContains + " instead of " + actual, actual.contains(expectedContains));
    }
}
