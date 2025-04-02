package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.bo.*;
import com.ilisi.Ecommerce.dto.LineBasketDTO;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.repository.BasketRepository;
import com.ilisi.Ecommerce.services.mapper.ClientMapper;
import com.ilisi.Ecommerce.services.mapper.LineBasketMapper;
import com.ilisi.Ecommerce.services.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LineBasketMapper lineBasketMapper;

    public List<LineBasketDTO> getAllLineBasket(int clientID) {
        Basket basket = basketRepository.findByClient(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Basket with client id " + clientID + " not found"));
        return basket.getLineBaskets().stream().map(lineBasketMapper::toDTO).toList();
    }

    public LineBasketDTO addProductToCart(int clientID, int productID, int quantity) {
        Client client = new ClientMapper().toBO(clientService.getClientById(clientID));
        Product product = new ProductMapper().toBO(productService.getProductById(productID));

        Basket basket = basketRepository.findByClient(clientID)
                .orElseGet(() -> {
                    Basket newBasket = new Basket();
                    newBasket.setClient(client);
                    newBasket.setBasketState(BasketState.EMPTY);
                    return basketRepository.save(newBasket);
                });

         LineBasket newLineBasket = basket.addLineBasket(product, quantity);
         basket.setActiveState();
         basketRepository.save(basket);

         Set<LineBasket> list =basket.getLineBaskets();

        return lineBasketMapper.toDTO(newLineBasket);
    }


    public void deleteBasket(int clientID) {
        Basket basket = basketRepository.findByClient(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Basket with client id " + clientID + " not found"));
        basketRepository.delete(basket);
    }




}
