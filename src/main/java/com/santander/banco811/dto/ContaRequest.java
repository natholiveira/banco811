package com.santander.banco811.dto;

import com.santander.banco811.model.TipoConta;
import lombok.Getter;

@Getter
public class ContaRequest {
    private TipoConta tipoConta;
    private Integer numero;
}
