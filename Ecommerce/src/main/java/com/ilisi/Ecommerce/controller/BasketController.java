package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.dto.LineBasketDTO;
import com.ilisi.Ecommerce.dto.addProductDTO;
import com.ilisi.Ecommerce.services.BasketService;
import com.ilisi.Ecommerce.services.LineBasketService;
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
    private  BasketService basketService;
   @Autowired
    private LineBasketService lineBasketService;

    @GetMapping("/{clientID}")
    public ResponseEntity<Response> getBasketItems(@PathVariable int clientID) {
        List<LineBasketDTO> lineBasketDTOS = basketService.getAllLineBasket(clientID);
        Response res = new Response(true, HttpStatus.OK, "success", lineBasketDTOS);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }
    @PostMapping("/{clientID}/add-product")
    public ResponseEntity<Response> addProductToCart (@PathVariable int clientID, @RequestBody addProductDTO product) {

        LineBasketDTO newLineBasket = basketService.addProductToCart(clientID, product.getProductID(), 1);
        Response res = new Response(true, HttpStatus.OK, "Product with id " + product.getProductID() + " added to cart with quantity 1", newLineBasket.getProduct());
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{clientID}")
    public ResponseEntity<Response> deleteBasket(@PathVariable int clientID) {
        basketService.deleteBasket(clientID);//Deleting the basket and all its items
        Response res =new Response(true, HttpStatus.OK, "Basket with client id " + clientID + " deleted successfully", null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

}