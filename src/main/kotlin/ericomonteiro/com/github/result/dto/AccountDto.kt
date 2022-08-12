package ericomonteiro.com.github.result.dto

import ericomonteiro.com.github.result.model.Account
import java.math.BigDecimal

class AccountDto(
    val id: String,
    val balance: BigDecimal
) {
    companion object {
        fun from(account: Account) = AccountDto(id = account.id, balance = account.balance)
    }
}