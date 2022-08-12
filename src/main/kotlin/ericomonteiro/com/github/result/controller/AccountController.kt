package ericomonteiro.com.github.result.controller

import ericomonteiro.com.github.result.dto.AccountDto
import ericomonteiro.com.github.result.dto.ErrorDto
import ericomonteiro.com.github.result.dto.WithdrawDto
import ericomonteiro.com.github.result.exception.BalanceInsufficientException
import ericomonteiro.com.github.result.service.account.AccountWithdrawService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountWithdrawService: AccountWithdrawService
) {

    @PostMapping("/v1/withdraw")
    fun v1Withdraw(@RequestBody withdraw: WithdrawDto): ResponseEntity<AccountDto> {
        return ResponseEntity.ok(
            accountWithdrawService.doWithdrawThrow(withdraw = withdraw)
        )
    }

    @PostMapping("/v2/withdraw")
    fun v2Withdraw(@RequestBody withdraw: WithdrawDto): ResponseEntity<AccountDto> {
        return ResponseEntity.ok(
            accountWithdrawService.doWithdrawResult(withdraw = withdraw).getOrThrow()
        )
    }

    @ExceptionHandler(BalanceInsufficientException::class)
    fun balanceInsufficientError(ex: BalanceInsufficientException): ResponseEntity<ErrorDto> =
        ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(
                ErrorDto(
                    "balance Insufficient",
                    ex.message
                )

            )

}