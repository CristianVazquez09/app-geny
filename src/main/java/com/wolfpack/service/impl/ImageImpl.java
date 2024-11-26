package com.wolfpack.service.impl;

import com.wolfpack.model.Image;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IImageRepo;
import com.wolfpack.repo.IProductRepo;
import com.wolfpack.repo.IServiceRepo;
import com.wolfpack.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ImageImpl extends CRUDServiceImpl<Image,Integer> implements IImageService {


    private final IImageRepo repo;
    private final IServiceRepo serviceRepo;
    private final IProductRepo productRepo;
    @Override
    protected IGenericRepo<Image, Integer> getRepo() {
        return repo;
    }

    @Override
    public Image findByIdService(Integer idService) {
        return serviceRepo.findImageByIdService(idService);
    }

    @Override
    public Image findByIdProduct(Integer idProduct) {
        return productRepo.findImageByIdProduct(idProduct);
    }

    
}

