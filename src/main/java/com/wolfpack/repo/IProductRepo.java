package com.wolfpack.repo;

import com.wolfpack.model.Image;
import com.wolfpack.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepo extends IGenericRepo<Product, Integer>{

    @Query("SELECT p.image FROM Product  p WHERE p.idProduct = :idProduct")
    Image findImageByIdProduct(@Param("idProduct") Integer idProduct);

    @Query("SELECT p.image.idImage FROM Product p WHERE p.idProduct = :idProduct")
    Integer findIdImage(@Param("idProduct") Integer idProduct);

}
