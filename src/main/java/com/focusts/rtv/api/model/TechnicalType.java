package com.focusts.rtv.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalType {

    I("I", "Interno"),
    E("E", "Externo"),
    N("N", "Não se Aplica");

    private final String value;
    private final String description;

}
