package com.lsantosart.mpapi.controllers;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class GeneralController {

    // Test form
    @GetMapping("/")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("preference_id", "252");
        return mv;
    }

    // Get payment request
    @PostMapping("/pay")
    public ModelAndView pay() {

        // Configura credenciais
        try {
            MercadoPago.SDK.setAccessToken("TEST-3725464736343104-092413-a7445fed10f1bded18db05de685c687d-415834449");
        } catch (MPConfException e) {
            e.printStackTrace();
        }

        // Cria um objeto de preferência
        Preference preference = new Preference();

        // Cria um item na preferência
        Item item = new Item();
        item.setTitle("Meu produto").setQuantity(1).setUnitPrice((float) 75.56);
        preference.appendItem(item);
        try {
            Preference response = preference.save();
            System.out.println(response);
        } catch (MPException e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }


}
