package com.ilisi.Ecommerce.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public enum BasketState {
    EMPTY,
    ACTIVE,
    VALIDATED,
    EXPIRED
}