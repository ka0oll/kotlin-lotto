package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LottoStatisticsTest {

    @Test
    fun `로또의 당첨 통계를 구한다`() {
        // given
        val winLotto = createLotto(listOf(1, 2, 3, 4, 5, 6))
        val fourthLotto = createLotto(listOf(1, 2, 3, 7, 8, 9))
        val missLotto = createLotto(listOf(7, 8, 9, 10, 11, 12))
        val lottoPaper = LottoPaper(listOf(fourthLotto, missLotto))

        // when
        val lottoStatistics: LottoStatistics =
            lottoPaper.doStatistics(Money(10_000), WinLotto(winLotto, LottoNum.from(20)))

        // then
        assertThat(lottoStatistics.earningsRate).isEqualTo(0.5)
    }

    private fun createLotto(nums: List<Int>): Lotto {
        return Lotto(nums.map { LottoNum.from(it) })
    }
}
