package com.ilisi.Ecommerce.services.mapper;

import com.ilisi.Ecommerce.bo.LineBasket;
import com.ilisi.Ecommerce.dto.LineBasketDTO;
import org.springframework.stereotype.Component;

@Component
public class LineBasketMapper implements IMapper<LineBasket, LineBasketDTO> {

    @Override
    public LineBasketDTO toDTO(LineBasket bo) {
        if (bo == null) {
            return null;
        }
        LineBasketDTO dto = new LineBasketDTO();
        dto.setBasketLineID(bo.getBasketLineID());
        dto.setProduct(new ProductMapper().toDTO(bo.getProduct()));
        dto.setBasket(new BasketMapper().toDTO(bo.getBasket()));
        dto.setQuantity(bo.getQuantity());
        return dto;
    }

    @Override
    public LineBasket toBO(LineBasketDTO dto) {
        if (dto == null) {
            return null;
        }
        LineBasket lineBasket = new LineBasket();
        lineBasket.setProduct(new ProductMapper().toBO(dto.getProduct()));
        lineBasket.setBasket(new BasketMapper().toBO(dto.getBasket()));
        lineBasket.setQuantity(dto.getQuantity());
        return lineBasket;
    }
}