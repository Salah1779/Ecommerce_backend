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

import java.util.Date;
import java.util.List;
import java.util.Optional;


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
LineBasketService lineBasketService;
    @Autowired
    private LineBasketMapper lineBasketMapper;

    public List<LineBasketDTO> getAllLineBasket(int clientID) {
        Basket basket = basketRepository.findByClientID(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Basket with client id " + clientID + " not found"));

        return lineBasketService.findAllByBasketID(basket.getBasketID());
    }

    public LineBasketDTO addProductToCart(int clientID, int productID, int quantity) {
        Client client = clientMapper.toBO(clientService.getClientById(clientID));
        Product product = productMapper.toBO(productService.getProductById(productID));
        Basket basket ;
        Optional<Basket> existedBasket = basketRepository.findByClientID(clientID);
        if(!existedBasket.isPresent()){
                    basket = new Basket();
                    basket.setCreatedDate(new Date());
                    basket.setClient(client);
                    client.setBasket(basket);
                    basket.setBasketState(BasketState.EMPTY);

        }else {basket=existedBasket.get();}

         LineBasket newLineBasket = basket.addLineBasket(product, quantity);
         lineBasketService.saveLineBasket(newLineBasket);
         basket.setActiveState();
         basketRepository.save(basket);

        return lineBasketMapper.toDTO(newLineBasket);
    }


    public void deleteBasket(int clientID) {
        Basket basket = basketRepository.findByClientID(clientID)
                .orElseThrow(() -> new ResourceNotFoundException("Basket with client id " + clientID + " not found"));
        basketRepository.delete(basket);
    }




}
