package lunets.co.henez.services.impl

import com.braintreegateway.BraintreeGateway
import com.braintreegateway.Environment
import com.braintreegateway.TransactionRequest
import lunets.co.henez.dto.PaymentDetails
import lunets.co.henez.services.PaymentService
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class PaymentServiceImpl : PaymentService {
    private val gateway = BraintreeGateway(
        Environment.SANDBOX,
        "your_merchant_id",
        "your_public_key",
        "your_private_key"
    )

    override fun commitPayment(userId: String, paymentDetails: PaymentDetails) {

    }
}