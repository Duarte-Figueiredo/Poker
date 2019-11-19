import org.junit.Assert
import org.junit.Test

class PokerResolverTest {

    @Test
    fun `On valid input returns correct answer`() {
        listOf(
                Pair(listOf(1, 2, 3, 4, 5), "Highest card :5"),
                Pair(listOf(1, 2, 13, 4, 5), "Highest card :13"),
                Pair(listOf(4, 2, 13, 4, 5), "Pair of :4"),
                Pair(listOf(4, 2, 4, 10, 4), "Trio of :4"),
                Pair(listOf(4, 2, 10, 2, 4), "Pairs of :4 and :2"),
                Pair(listOf(4, 6, 4, 6, 4), "Full House of 3 :4 and 2 :6"),
                Pair(listOf(8, 8, 8, 6, 8), "Four of :8")
        ).forEach { (input, expected) ->
            val actual = PokerResolver.solve(input)

            Assert.assertEquals("Expected $input to $expected", expected, actual)
        }

    }

    @Test
    fun `On invalid input throws exception`() {
        listOf(
                listOf(1, 2, 3, 4, 55),
                listOf(1, 2, 3, 4, 5, 5)
        ).forEach { input ->

            val result = kotlin.runCatching {
                PokerResolver.solve(input)
            }

            Assert.assertTrue(result.isFailure)
        }
    }
}
