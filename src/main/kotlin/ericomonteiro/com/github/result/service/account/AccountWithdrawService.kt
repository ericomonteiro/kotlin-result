package ericomonteiro.com.github.result.service.account

import ericomonteiro.com.github.result.dto.AccountDto
import ericomonteiro.com.github.result.dto.WithdrawDto
import ericomonteiro.com.github.result.exception.BalanceInsufficientException
import ericomonteiro.com.github.result.model.Account
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class AccountWithdrawService {

    fun doWithdrawThrow(withdraw: WithdrawDto): AccountDto {
        val account = getSomeAccount()

        if (account.balance < withdraw.value) {
            throw BalanceInsufficientException(account.balance, withdraw.value)
        }

        account.balance -= withdraw.value
        return AccountDto.from(account)
    }

    fun doWithdrawResult(withdraw: WithdrawDto): Result<AccountDto> {
        val account = getSomeAccount()

        if (account.balance < withdraw.value) {
            return Result.failure(BalanceInsufficientException(account.balance, withdraw.value))
        }

        account.balance -= withdraw.value
        return Result.success(AccountDto.from(account))
    }


    private fun getSomeAccount() = Account(BigDecimal(100))
}