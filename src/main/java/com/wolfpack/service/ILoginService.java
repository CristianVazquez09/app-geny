package com.wolfpack.service;

import com.wolfpack.dto.LoginDTO;
import com.wolfpack.model.Client;
import com.wolfpack.model.User;

public interface ILoginService{

    User authenticateUser (LoginDTO login) throws Exception;

    Client findByClientByIdUser(Integer idUser) throws Exception;

}
