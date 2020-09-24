package com.lsantosart.mpapi.controllers;

import com.lsantosart.mpapi.models.Customer;
import com.lsantosart.mpapi.services.PaymentService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@RestController
public class GeneralController {

    @Autowired
    PaymentService paymentService = new PaymentService();


    // Test form
    @GetMapping("/")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("test", "teste");
        return mv;
    }

    // Get payment request
    @PostMapping("/pay")
    public ModelAndView pay(Customer customer, RedirectAttributes attributes) throws MPConfException {
        
        ModelAndView mv = new ModelAndView("success");

        Payment status = paymentService.pay(customer);
        
        if (status.getStatus().toString() == "approved") {
            mv.addObject("status", "Pagamento aprovado");
        }else{
            mv.addObject("status", "Pagamento não aprovado, verifique suas credenciais...");
        }
        
        return mv;

    }


}
