package com.lsantosart.mpapi.controllers;

import com.lsantosart.mpapi.models.Customer;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.payment.Payer;
import com.mercadopago.resources.datastructures.preference.Item;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class GeneralController {

    // Test form
    @GetMapping("/")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    // Get payment request
    @PostMapping("/pay")
    public ModelAndView pay(@RequestBody Customer customer) throws MPConfException {
        
        MercadoPago.SDK.setAccessToken("TEST-3725464736343104-092413-a7445fed10f1bded18db05de685c687d-415834449");
        
        //...
        Payment payment = new Payment();
        payment.setTransactionAmount(123f)
            .setToken(customer.getToken())
            .setDescription("Intelligent Rubber Table")
            .setInstallments(customer.getInstallments())
            .setPaymentMethodId(customer.getPayment_method_id())
            .setIssuerId(customer.getIssuer_id())
            .setPayer(new Payer()
            .setEmail("layla.kohler@hotmail.com"));
        
        // Armazena e envia o pagamento
        try {
            payment.save();
        } catch (MPException e) {
            e.printStackTrace();
        }


        // Imprime o status do pagamento
        System.out.println(payment.getStatus());


        ModelAndView mv = new ModelAndView("index");
        return mv;
    }


}
