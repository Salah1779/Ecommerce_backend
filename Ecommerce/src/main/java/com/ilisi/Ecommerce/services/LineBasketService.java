package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.bo.LineBasket;
import com.ilisi.Ecommerce.dto.LineBasketDTO;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.repository.LineBasketRepository;
import com.ilisi.Ecommerce.services.mapper.LineBasketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LineBasketService {
    @Autowired
    private LineBasketMapper lineBasketMapper;
    @Autowired
    private LineBasketRepository lineBasketRepository;

    public void deleteLineBasket(int basketLineID) {
        LineBasket lineBasket = getBasketLine(basketLineID);
        lineBasketRepository.delete(lineBasket);
    }

    public List<LineBasketDTO>findAllByBasketID(int basketID) {
        List<LineBasket> lineBaskets = lineBasketRepository.getByBasketID(basketID);
        return lineBaskets.stream().map(lineBasketMapper::toDTO).collect(Collectors.toList());

    }
    public void saveLineBasket(LineBasket lineBasket) {
        lineBasketRepository.save(lineBasket);
    }


    public LineBasket getBasketLine(int basketLineID) {
        return lineBasketRepository.findById(basketLineID)
                .orElseThrow(() -> new ResourceNotFoundException("LineBasket  with id " + basketLineID + " not found"));
    }


    public LineBasketDTO updateQuantity(int basketLineID, boolean increase) {
        LineBasket lineBasket = getBasketLine(basketLineID);
        boolean updated = increase ? lineBasket.increaseQuantity() : lineBasket.decreaseQuantity();
        if (updated) {
            lineBasket=lineBasketRepository.save(lineBasket);
        }
      return new LineBasketMapper().toDTO(lineBasket);
    }
}

