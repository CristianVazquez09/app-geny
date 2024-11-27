package com.wolfpack.service;

import com.wolfpack.model.Appointment;
import com.wolfpack.model.Client;
import com.wolfpack.model.Employee;

import java.util.List;

public interface IClientService extends ICRUDService<Client, Integer> {

    Client saveWithEncryptedPassword(Client client) throws Exception;

    Client updateWithExistingPassword(Client client, Integer id) throws Exception;

    List<Appointment> findAllAppointments (Integer id) throws Exception;

}
