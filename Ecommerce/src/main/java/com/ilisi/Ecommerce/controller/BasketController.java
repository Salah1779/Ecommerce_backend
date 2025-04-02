package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.bo.Basket;
import com.ilisi.Ecommerce.bo.LineBasket;
import com.ilisi.Ecommerce.dto.LineBasketDTO;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.services.BasketService;
import com.ilisi.Ecommerce.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
   @Autowired
    private BasketService basketService;


    @GetMapping("/{clientID}")
    public ResponseEntity<Response> getBasketItems(@PathVariable int clientID) {
        List<LineBasketDTO> lineBasketDTOS = basketService.getAllLineBasket(clientID);
        Response res = new Response(true, HttpStatus.OK, "success", lineBasketDTOS);
        return new ResponseEntity<Response>(res, HttpStatus.OK);

    }
    @PostMapping("/{clientID}/add-product")
    public ResponseEntity<Response> addProductToCart (@PathVariable int clientID, @RequestBody int productID) {

        LineBasketDTO newLineBasket = basketService.addProductToCart(clientID, productID, 1);
        Response res = new Response(true, HttpStatus.OK, "Product with id " + productID + " added to cart", newLineBasket);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{clientID}")
    public ResponseEntity<Response> deleteBasket(@PathVariable int clientID) {
        basketService.deleteBasket(clientID);
        Response res =new Response(true, HttpStatus.OK, "Basket with client id " + clientID + " deleted successfully", null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

}