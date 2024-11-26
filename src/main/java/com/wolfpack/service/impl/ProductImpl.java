package com.wolfpack.service.impl;

import com.wolfpack.model.Image;
import com.wolfpack.model.Product;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IImageRepo;
import com.wolfpack.repo.IProductRepo;
import com.wolfpack.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductImpl extends CRUDServiceImpl<Product,Integer> implements IProductService {


    private final IProductRepo repo;
    private final IImageRepo imageRepo;
    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }


    @Override
    public byte[] updateImagenProduct(Image image, Integer idProduct) throws Exception {
        image.setIdImage(repo.findIdImage(idProduct));
        imageRepo.save(image);
        return image.getData();
    }
}

