package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.bo.LineBasket;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.repository.LineBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LineBasketService {

    @Autowired
    private LineBasketRepository lineBasketRepository;


    public void deleteLineBasket(int basketLineID) {
        LineBasket lineBasket = getBasketLine(basketLineID);
        lineBasketRepository.delete(lineBasket);
    }

    public void saveLineBasket(LineBasket lineBasket) {
        lineBasketRepository.save(lineBasket);
    }


    public LineBasket getBasketLine(int basketLineID) {
        return lineBasketRepository.findById(basketLineID)
                .orElseThrow(() -> new ResourceNotFoundException("LineBasket  with id " + basketLineID + " not found"));
    }


    public void updateQuantity(int basketLineID, boolean increase) {
        LineBasket lineBasket = getBasketLine(basketLineID);
        boolean updated = increase ? lineBasket.increaseQuantity() : lineBasket.decreaseQuantity();
        if (updated) {
            lineBasketRepository.save(lineBasket);
        }
    }
}

