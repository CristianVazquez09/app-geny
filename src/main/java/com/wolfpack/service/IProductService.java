package com.wolfpack.service;

import com.wolfpack.model.Image;
import com.wolfpack.model.Product;

public interface IProductService extends ICRUDService<Product, Integer> {

    byte[] updateImagenProduct(Image image, Integer idProduct) throws Exception;

}
