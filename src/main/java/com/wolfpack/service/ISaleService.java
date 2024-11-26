package com.wolfpack.service;

import com.wolfpack.model.Sale;

public interface ISaleService extends ICRUDService<Sale, Integer> {

    Sale saveWithValidation(Sale sale) throws Exception;
    Sale updateWithValidation(Sale sale, Integer id)  throws Exception;

}
