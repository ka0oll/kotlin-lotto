package caculator

internal data class NaturalNumber(val value: Int) {
    init {
        validate()
    }

    constructor(value: String) : this(
        value.toIntOrNull() ?: throw IllegalArgumentException("invalid string")
    )

    internal operator fun plus(naturalNumber: NaturalNumber): NaturalNumber {
        return NaturalNumber(this.value + naturalNumber.value)
    }

    internal operator fun minus(naturalNumber: NaturalNumber): NaturalNumber {
        return NaturalNumber(this.value - naturalNumber.value)
    }

    internal operator fun times(naturalNumber: NaturalNumber): NaturalNumber {
        return NaturalNumber(this.value * naturalNumber.value)
    }

    internal operator fun div(naturalNumber: NaturalNumber): NaturalNumber {
        return NaturalNumber(this.value / naturalNumber.value)
    }

    private fun validate() {
        require(this.value >= MIN_VALUE) {
            "number must be positive"
        }
    }

    companion object {
        val ZERO = NaturalNumber(0)
        private const val MIN_VALUE: Int = 0
    }
}
