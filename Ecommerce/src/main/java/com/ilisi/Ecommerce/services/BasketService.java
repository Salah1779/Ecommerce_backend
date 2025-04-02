package com.ilisi.Ecommerce.services;

import com.ilisi.Ecommerce.bo.Basket;
import com.ilisi.Ecommerce.bo.BasketState;
import com.ilisi.Ecommerce.bo.Client;
import com.ilisi.Ecommerce.bo.Product;
import com.ilisi.Ecommerce.exception.ResourceNotFoundException;
import com.ilisi.Ecommerce.repository.BasketRepository;
import com.ilisi.Ecommerce.services.mapper.ClientMapper;
import com.ilisi.Ecommerce.services.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private  LineBasketService lineBasketService;



    public Basket addProductToCart(int clientID, int productID, int quantity) {
        Client client = clientMapper.toBO(clientService.getClientById(clientID));
        Product product = productMapper.toBO(productService.getProductById(productID));

        Basket basket = basketRepository.findByClient(clientID)
                .orElseGet(() -> {
                    Basket newBasket = new Basket();
                    newBasket.setClient(client);
                    newBasket.setBasketState(BasketState.EMPTY);
                    return basketRepository.save(newBasket);
                });

        basket.addLineBasket(product, quantity);
        basket.setActiveState();
        return basketRepository.save(basket);
    }


    public void deleteBasket(int clientID) {
        Basket basket = basketRepository.findByClient(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Basket with client id " + clientID + " not found"));
        basketRepository.delete(basket);
    }


}
