package com.ilisi.Ecommerce.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



public enum BasketState {
    EMPTY,
    ACTIVE,
    VALIDATED,
    EXPIRED
}