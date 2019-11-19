object PokerResolver {

    fun solve(cards: List<Int>): String {
        validateCards(cards)

        val cardCount = getCount(cards)

        return when (cardCount.size) {
            5 -> "Highest card :${getHighestCard(cardCount)}"
            4 -> "Pair of :${getSimplePair(cardCount)}"
            3 -> getTrioOrTwoPairs(cardCount)
            2 -> getFullHouseOrFourOfAKind(cardCount)
            else -> throw RuntimeException("Invalid size ${cardCount.size}")
        }
    }

    private fun validateCards(cards: List<Int>) {
        val size = cards.size

        if (size != 5)
            throw throw RuntimeException("Invalid size $size")

        cards.firstOrNull { it < 1 || it > 13 }?.let {
            throw RuntimeException("Invalid card: $it")
        }
    }

    private fun getCount(cards: List<Int>): List<Pair<Int, Int>> {
        val cardHashSet = mutableMapOf<Int, Int>()

        cards.forEach {
            val value = cardHashSet[it] ?: 0

            cardHashSet[it] = value + 1
        }

        return cardHashSet.map {
            Pair(it.key, it.value)
        }
    }

    private fun getHighestCard(cardsCount: List<Pair<Int, Int>>) = cardsCount
            .maxBy { it.first }
            ?.first
            ?: throw RuntimeException("Invalid State")

    private fun getSimplePair(cardsCount: List<Pair<Int, Int>>) = cardsCount
            .firstOrNull { it.second > 1 }
            ?.first
            ?: throw RuntimeException("Invalid State")

    private fun getTrioOrTwoPairs(cardsCount: List<Pair<Int, Int>>) = cardsCount
            .filter { it.second > 1 }
            .let {
                when (it.size) {
                    1 -> "Trio of :${it.first().first}"
                    2 -> "Pairs of :${it.first().first} and :${it.last().first}"
                    else -> throw RuntimeException("Invalid State")
                }
            }

    private fun getFullHouseOrFourOfAKind(cardsCount: List<Pair<Int, Int>>) = cardsCount
            .filter { it.second > 1 }
            .sortedByDescending { it.second }
            .let {
                when (it.size) {
                    1 -> "Four of :${it.first().first}"
                    2 -> "Full House of 3 :${it.first().first} and 2 :${it.last().first}"
                    else -> throw RuntimeException("Invalid State")
                }
            }
}
