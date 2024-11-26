package com.wolfpack.service;

import com.wolfpack.model.Image;
import com.wolfpack.model.Service;

import java.util.List;

public interface IServiceService extends ICRUDService<Service, Integer>{

    Service saveWithValidation(Service service) throws Exception;
    Service updateWithValidation(Service service, Integer id)  throws Exception;

    List<Image> findAllImages() throws Exception;

    byte[] updateImagenService( Image image, Integer idService) throws Exception;
}
