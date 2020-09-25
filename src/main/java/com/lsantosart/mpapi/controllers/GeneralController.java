package com.lsantosart.mpapi.controllers;

import com.lsantosart.mpapi.services.CheckoutService;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.Preference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class GeneralController {

    @Autowired
    CheckoutService checkoutService = new CheckoutService();


    // Checkou pro integration
    @GetMapping("/")
    public ModelAndView form() throws MPConfException {

        ModelAndView mv = new ModelAndView("index");
        Preference preference = checkoutService.checkout();

        mv.addObject("preference", preference);
        mv.addObject("total", 119.90);
        return mv;
    }

    //Backing URL
    @PostMapping("/processar_pagamento")
    public ModelAndView success() {
        ModelAndView mv = new ModelAndView("success");
        return mv;
    }

}
