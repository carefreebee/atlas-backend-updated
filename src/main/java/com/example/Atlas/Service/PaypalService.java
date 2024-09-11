package com.example.Atlas.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.Atlas.Entity.PaypalEntity;
import com.example.Atlas.Repository.PaypalRepository;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaypalService {
    
    private final APIContext apiContext;
    private final PaypalRepository paypalRepository;

    public Payment createPayment(
        Double total,
        String currency,
        String method,
        String intent,
        String description,
        String firstname,
        String lastname,
        String departmentname,
        String cancelUrl,
        String successsUrl
    ) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description + " | Department: " + departmentname);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(firstname);
        payerInfo.setLastName(lastname);
        payer.setPayerInfo(payerInfo);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successsUrl);

        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);

        // Save payment information in the database
        PaypalEntity paypalEntity = new PaypalEntity();
        paypalEntity.setPaymentId(createdPayment.getId());  // Save the PayPal payment ID
        paypalEntity.setFirstname(firstname);
        paypalEntity.setLastname(lastname);
        paypalEntity.setDepartmentname(departmentname);
        paypalEntity.setAmount(total);
        paypalEntity.setCurrency(currency);
        paypalEntity.setDescription(description);
        paypalEntity.setStatus(createdPayment.getState());
        paypalEntity.setPaid(false);  // Initially set to false

        paypalRepository.save(paypalEntity);

        return createdPayment;
    }

    public Payment executePayment(
        String paymentId,
        String payerId
    ) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        // return payment.execute(apiContext, paymentExecution);
        Payment executedPayment = payment.execute(apiContext, paymentExecution);

        // Update the database record
        updatePaymentStatus(paymentId, executedPayment.getState().equals("approved"));

        return executedPayment;
    }

    private void updatePaymentStatus(String paymentId, boolean isSuccessful) {
        PaypalEntity paypalEntity = paypalRepository.findByPaymentId(paymentId)
            .orElseThrow(() -> new EntityNotFoundException("Payment not found for ID: " + paymentId));
    
        paypalEntity.setPaid(isSuccessful);
        paypalEntity.setStatus(isSuccessful ? "COMPLETED" : "FAILED");
    
        paypalRepository.save(paypalEntity);
    }
}
