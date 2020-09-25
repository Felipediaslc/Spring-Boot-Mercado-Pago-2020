package com.lsantosart.mpapi.services;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import com.mercadopago.resources.datastructures.preference.Shipments;

import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    
    private String accessToken = "TEST-3725464736343104-092413-a7445fed10f1bded18db05de685c687d-415834449";

    public Preference checkout() throws MPConfException {

        MercadoPago.SDK.setAccessToken(accessToken);

        

        // Cria um objeto de preferência
        Preference preference = new Preference();

        //Cria uma URL de retorno
        BackUrls backUrls = new BackUrls(
            "http://localhost:8080/success",
            "http://localhost:8080/pending",
            "http://localhost:8080/failure"
        );

        preference.setBackUrls(backUrls);
        preference.setPayer(setPayerDetails());

        // Cria um item na preferência
        Item item = new Item();
        item.setTitle("Meu produto")
            .setQuantity(1)
            .setDescription("1x bola, 1x carrinho, 2x camisetas")
            .setUnitPrice((float) 99.90);
        preference.appendItem(item);
        preference.setShipments(new Shipments()
            .setCost(20.00f)
        );
        try {
            preference.save();
        } catch (MPException e) {
            e.printStackTrace();
        }

        return preference;

    }

    // Define os detalhes do pagador
    public Payer setPayerDetails() {

        Payer payer = new Payer();

        payer.setName("Cliente");
        payer.setSurname("De testes");
        payer.setPhone(new Phone()
            .setAreaCode("61")
            .setNumber("9 9999-9999")
        );

        payer.setAddress(new Address()
            .setStreetName("streetName")
            .setStreetNumber(25)
            .setZipCode("71800500")
        );

        payer.setEmail("testespagar@gmail.com");
        payer.setDateCreated("11/02/2020");
        payer.setIdentification(new Identification()
            //CPF
            .setNumber("941.477.820-94")
            .setType("Cliente")
        );

        return payer;
    }
    
}
