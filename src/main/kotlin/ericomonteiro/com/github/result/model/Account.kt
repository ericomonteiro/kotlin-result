package ericomonteiro.com.github.result.model

import java.math.BigDecimal
import java.util.UUID

class Account(
    val id: String,
    var balance: BigDecimal
) {
    constructor(balance: BigDecimal) : this(id = UUID.randomUUID().toString(), balance = balance)
}