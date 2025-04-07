package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.Basket;
import com.ilisi.Ecommerce.dto.BasketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper implements IMapper<Basket, BasketDTO> {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public BasketDTO toDTO(Basket bo) {
        if (bo == null) {
            return null;
        }
        BasketDTO dto = new BasketDTO();
        dto.setBasketID(bo.getBasketID());
        dto.setBasketState(bo.getBasketState());
        dto.setCreatedDate(bo.getCreatedDate());
        dto.setClient(clientMapper.toDTO(bo.getClient()));
        return dto;
    }

    @Override
    public Basket toBO(BasketDTO dto) {
        if (dto == null) {
            return null;
        }
        Basket basket = new Basket();
        basket.setBasketID(dto.getBasketID());
        basket.setBasketState(dto.getBasketState());
        basket.setCreatedDate(dto.getCreatedDate());
         basket.setClient(clientMapper.toBO(dto.getClient()));
        return basket;
    }


}