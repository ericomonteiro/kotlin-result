package ericomonteiro.com.github.result.exception

import java.math.BigDecimal

class BalanceInsufficientException(
    private val accountBalance: BigDecimal,
    private val valueWithdraw: BigDecimal
): RuntimeException() {

    override val message: String
        get() = "Insufficient balance - account balance = $accountBalance - withdraw = $valueWithdraw"
}