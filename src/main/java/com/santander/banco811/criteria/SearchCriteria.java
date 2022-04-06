package com.santander.banco811.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter @AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
