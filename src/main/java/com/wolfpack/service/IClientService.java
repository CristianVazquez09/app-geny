package com.wolfpack.service;

import com.wolfpack.model.Client;
import com.wolfpack.model.Employee;

public interface IClientService extends ICRUDService<Client, Integer> {

    Client saveWithEncryptedPassword(Client client) throws Exception;

    Client updateWithExistingPassword(Client client, Integer id) throws Exception;

}
