package lunets.co.henez.services

import lunets.co.henez.dto.PaymentDetails

interface PaymentService {
    fun commitPayment(userId: String, paymentDetails: PaymentDetails)
}