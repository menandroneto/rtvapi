package com.focusts.rtv.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalType {

    INTERN("Interno"),
    EXTERN("Externo"),
    NONE("Nenhum");

    private final String description;

}
