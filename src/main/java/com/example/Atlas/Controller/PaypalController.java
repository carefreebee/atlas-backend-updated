package com.example.Atlas.Controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Atlas.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
        @RequestParam("method") String method,
        @RequestParam("amount") String amount,
        @RequestParam("currency") String currency,
        @RequestParam("description") String description,
        @RequestParam("firstname") String firstname,
        @RequestParam("lastname") String lastname,
        @RequestParam("departmentname") String departmentname
    ){
        try{
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(
                10.0,
                "USD",
                "paypal",
                "sale",
                description,
                firstname,
                lastname,
                departmentname,
                cancelUrl,
                successUrl
            );

            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e){
            log.error("Error occurred: ", e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public RedirectView paymentSuccess(
        @RequestParam("paymentId") String paymentId,
        @RequestParam("PayerID") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                // Redirect to frontend success page
                return new RedirectView("http://localhost:3000/success?paymentId=" + paymentId + "&PayerID=" + payerId);
            }  else {
                // Redirect to error page if payment is not approved
                return new RedirectView("http://localhost:3000/error?reason=payment_not_approved");
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred: ", e);
            // Redirect to error page with exception details
            return new RedirectView("http://localhost:3000/error?reason=payment_exception&message=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        }
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }
}
