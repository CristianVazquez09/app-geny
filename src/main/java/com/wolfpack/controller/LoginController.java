package com.wolfpack.controller;

import com.wolfpack.dto.AppointmentDTO;
import com.wolfpack.dto.ClientDTO;
import com.wolfpack.dto.LoginDTO;
import com.wolfpack.dto.UserDTO;
import com.wolfpack.model.Appointment;
import com.wolfpack.model.Client;
import com.wolfpack.model.User;
import com.wolfpack.service.ILoginService;
import com.wolfpack.service.impl.LoginImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {


    private final ILoginService loginService;
    private final ModelMapper modelMapper;
    private final LoginImpl loginImpl;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {

        User user = loginService.authenticateUser(loginDTO);

        return ResponseEntity.ok(convertToDTO(user));

    }

    @GetMapping("/findClient/{idUser}")
    public ResponseEntity<ClientDTO> findClient (@PathVariable("idUser")Integer idUser) throws Exception{
        Client client = loginService.findByClientByIdUser(idUser);
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);

        return ResponseEntity.ok(clientDTO);
    }

    private UserDTO convertToDTO(User obj){
        return modelMapper.map(obj, UserDTO.class);
    }

    private User convertToEntity (UserDTO dto){
        return modelMapper.map(dto, User.class);
    }
}
