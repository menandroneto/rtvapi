package com.focusts.rtv.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalType {

    INTERN("I", "Interno"),
    EXTERN("E", "Externo"),
    NONE("N", "Nenhum");

    private final String value;
    private final String description;

}
