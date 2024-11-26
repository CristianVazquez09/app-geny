package com.wolfpack.service;

import com.wolfpack.model.Image;

public interface IImageService extends ICRUDService<Image, Integer> {

        Image findByIdService(Integer idService);
        Image findByIdProduct(Integer idProduct);
}
