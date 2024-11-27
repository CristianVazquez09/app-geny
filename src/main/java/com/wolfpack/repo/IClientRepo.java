package com.wolfpack.repo;

import com.wolfpack.model.Client;
import com.wolfpack.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClientRepo extends IGenericRepo<Client, Integer>{
    @Query("SELECT e.user FROM Client e WHERE e.idClient = :clientId")
    User findUserByClienteId(@Param("clientId") Integer clientId) ;

    // Query para obtener el cliente basado en el ID de usuario
    @Query("SELECT c FROM Client c WHERE c.user.idUser = :idUser")
    Client findClientByUserId(@Param("idUser") Integer idUser);

}
