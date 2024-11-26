package com.wolfpack.service.impl;

import com.wolfpack.model.Client;
import com.wolfpack.model.User;
import com.wolfpack.model.enums.RoleEnum;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IClientRepo;
import com.wolfpack.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientImpl extends CRUDServiceImpl<Client,Integer> implements IClientService{


    private final IClientRepo repo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleEnum ROLE_CLIENT = RoleEnum.CLIENT;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }

    @Override
    public Client saveWithEncryptedPassword(Client client) throws Exception {
        String passwordEncode = passwordEncoder.encode(client.getUser().getPassword());
        client.getUser().setRole(ROLE_CLIENT);
        client.getUser().setPassword(passwordEncode);

        return repo.save(client);
    }

    @Override
    public Client updateWithExistingPassword(Client client, Integer id) throws Exception {

        User userFind = repo.findUserByClienteId(id);
        client.getUser().setIdUser(userFind.getIdUser());
        client.getUser().setPassword(userFind.getPassword());
        client.getUser().setRole(ROLE_CLIENT);
        return repo.save(client);
    }
}

