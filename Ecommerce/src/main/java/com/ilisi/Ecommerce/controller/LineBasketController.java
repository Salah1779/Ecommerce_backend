package com.ilisi.Ecommerce.controller;

import com.ilisi.Ecommerce.bo.LineBasket;
import com.ilisi.Ecommerce.dto.LineBasketDTO;
import com.ilisi.Ecommerce.services.LineBasketService;
import com.ilisi.Ecommerce.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket-lines")
public class LineBasketController {

    @Autowired
    private LineBasketService lineBasketService;

    @DeleteMapping("/{basketLineID}")
    public ResponseEntity<Response> deleteLineBasket(@PathVariable int basketLineID) {
        lineBasketService.deleteLineBasket(basketLineID);
        Response res = new Response(true, HttpStatus.OK, "LineBasket with id " + basketLineID + " deleted successfully", null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }

    @PatchMapping("/{basketLineID}/quantity")
    public ResponseEntity<Response> updateQuantity(@PathVariable int basketLineID, @RequestParam boolean increase) {
        LineBasketDTO updated= lineBasketService.updateQuantity(basketLineID, increase);
        Response res = new Response(true, HttpStatus.OK, "LineBasket with id " + basketLineID + " updated successfully", updated);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    }
}

