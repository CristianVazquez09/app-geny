package com.wolfpack.repo;

import com.wolfpack.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    User findByEmail(String email);

}
