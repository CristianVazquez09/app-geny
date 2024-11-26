package com.wolfpack.repo;

import com.wolfpack.model.Image;
import com.wolfpack.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServiceRepo extends IGenericRepo<Service, Integer>{

    @Query("SELECT s.image FROM Service s WHERE s.image IS NOT NULL")
    List<Image> findAllImages() throws Exception;

    @Query("SELECT s.image FROM Service s WHERE s.idService = :idService")
    Image findImageByIdService(@Param("idService") Integer idService);


    @Query("SELECT s.image.idImage FROM Service s WHERE s.idService = :idService")
    Integer findIdImage(@Param("idService") Integer idService);


}
