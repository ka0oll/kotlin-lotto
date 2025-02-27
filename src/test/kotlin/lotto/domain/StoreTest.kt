package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StoreTest {

    @Test
    fun `금액에 맞게 로또를 발급해준다`() {
        // given
        val nums = listOf(1, 2, 3, 4, 5, 6)
        val lotto = createLotto(nums)
        val expect = LottoPaper(listOf(lotto, lotto))

        // when
        val result: LottoPaper = Store(lottoNumsGenerator = StaticLottoNumsGenerator(nums)).sell(Money(2000))

        // then
        assertThat(result).isEqualTo(expect)
    }

    @Test
    fun `금액에 맞게 수동 로또와 자동 로또를 함께 구매 할 수 있다`() {
        // given
        val autoNums = listOf(1, 2, 3, 4, 5, 6)
        val autoLotto = createLotto(autoNums)
        val selfLottoNums = listOf(7, 8, 9, 4, 10, 11).map { LottoNum(it) }

        val store = Store(lottoNumsGenerator = StaticLottoNumsGenerator(autoNums))
        val expect = LottoPaper(listOf(Lotto(selfLottoNums), autoLotto, autoLotto))

        // when
        val result: LottoPaper = store.sell(Money(3000), listOf(selfLottoNums))

        // then
        assertThat(result).isEqualTo(expect)
    }
}
