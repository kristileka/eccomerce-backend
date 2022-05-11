package lunets.co.henez.services.impl

import com.braintreegateway.*
import lunets.co.henez.services.PaymentService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


@Service
class PaymentServiceImpl : PaymentService {
    private val gateway = BraintreeGateway(
        Environment.SANDBOX,
        "cz7skqxpw4cnkrf9",
        "qr4j925278wtxzn7",
        "748e9276add8563287d576674bda4bf9"
    )

    private fun currencyConverter(chargeAmount: Double): Double {
        return chargeAmount * 0.0083
    }

    fun round(value: Double, places: Int): Double {
        require(places >= 0)
        var bd = BigDecimal.valueOf(value)
        bd = bd.setScale(places, RoundingMode.HALF_UP)
        return bd.toDouble()
    }

    override fun commitPayment(nonce: String, chargeAmount: Double): Boolean {
        val request: TransactionRequest = TransactionRequest()
            .amount(getChargeAmountByCurrency(chargeAmount))
            .paymentMethodNonce(nonce)
            .deviceData(UUID.randomUUID().toString())
            .options()
            //This confirms the payment automatically.
            .submitForSettlement(true)
            .done()
        return try {
            val result: Result<Transaction>? = gateway.transaction().sale(request)
            result?.isSuccess ?: false
        } catch (ex: Exception) {
            false
        }
    }

    private fun getChargeAmountByCurrency(chargeAmount: Double): BigDecimal? {
        return BigDecimal(
            currencyConverter(chargeAmount).toString()
        ).setScale(2, RoundingMode.HALF_UP)
    }
}
