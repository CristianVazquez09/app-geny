package com.wolfpack.controller;

import com.wolfpack.dto.AppointmentDTO;
import com.wolfpack.dto.LoginDTO;
import com.wolfpack.dto.UserDTO;
import com.wolfpack.model.Appointment;
import com.wolfpack.model.User;
import com.wolfpack.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {


    private final ILoginService loginService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {

        User user = loginService.authenticateUser(loginDTO);

        return ResponseEntity.ok(convertToDTO(user));

    }

    private UserDTO convertToDTO(User obj){
        return modelMapper.map(obj, UserDTO.class);
    }

    private User convertToEntity (UserDTO dto){
        return modelMapper.map(dto, User.class);
    }
}
