package com.lsantosart.mpapi.services;

import com.lsantosart.mpapi.models.Customer;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment pay(Customer customer) throws MPConfException {

        MercadoPago.SDK.setAccessToken("TEST-3725464736343104-092413-a7445fed10f1bded18db05de685c687d-415834449");

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


        // Imprime as informações do pagamento
        System.out.println("==============================");
        System.out.println(payment.getStatus());
        System.out.println(payment.getId());
        System.out.println(payment.getDescription());
        System.out.println(payment.getStatementDescriptor());
        System.out.println("==============================");
        

        return payment;
    }

}
