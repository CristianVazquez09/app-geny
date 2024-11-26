package com.wolfpack.service.impl;

import com.wolfpack.dto.LoginDTO;
import com.wolfpack.model.User;
import com.wolfpack.repo.IUserRepo;
import com.wolfpack.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginImpl implements ILoginService {

    private final IUserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User authenticateUser(LoginDTO login) throws Exception {
        User user = userRepo.findByEmail(login.getEmail());

        if (user == null) {
            throw new Exception("Usuario no encontrado");
        }

        boolean isPasswordMatch = passwordEncoder.matches(login.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new Exception("Contraseña incorrecta");
        }

        return user;
    }
}

