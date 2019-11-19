import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PokerTest {

    private Poker poker;
    private int[] cards;
    private String expectedContains;

    @Before
    public void initialize() {
        poker = new Poker();
    }

    @Parameterized.Parameters
    public static Collection possibleCards() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4, 5}, "5"},
                {new int[]{5, 4, 3, 2, 1}, "5"},
                {new int[]{4, 2, 5, 1, 3}, "5"},
                {new int[]{4, 2, 5, 1, 4}, "4"},
                {new int[]{4, 2, 5, 2, 4}, "4"},
                {new int[]{4, 2, 5, 2, 4}, "2"},
                {new int[]{4, 2, 2, 2, 4}, "Full House"}
        });
    }

    public PokerTest(int[] cards, String expectedContains) {
        this.cards = cards;
        this.expectedContains = expectedContains;
    }

    @Test
    public void getHand() throws Exception {
        System.out.println("Cards Number is : " + expectedContains);

        String actual = poker.getHand(cards);

        Assert.assertTrue("Input " + Arrays.toString(cards) + " should contain " + expectedContains + " but it contained " + actual, actual.contains(expectedContains));
    }
}
