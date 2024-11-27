package com.wolfpack.controller;

import com.wolfpack.dto.AppointmentDTO;
import com.wolfpack.dto.ClientDTO;
import com.wolfpack.dto.ClientRegistrationDTO;
import com.wolfpack.model.Appointment;
import com.wolfpack.model.Client;
import com.wolfpack.service.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll () throws Exception {
        List<ClientDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Client obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }
    @GetMapping("/findAllAppointments/{id}")
    public ResponseEntity<List<AppointmentDTO>> findAllAppointments (@PathVariable("id") Integer id) throws Exception {

        List<AppointmentDTO> obj = service.findAllAppointments(id).stream().map(e -> mapper.map(e, AppointmentDTO.class)).toList();

        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClientRegistrationDTO dto) throws Exception{

        Client obj = service.saveWithEncryptedPassword(convertToEntityRequest(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getIdClient()).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto, @PathVariable("id") Integer id) throws Exception {
        dto.setIdClient(id);
        Client obj = service.updateWithExistingPassword(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private Client convertToEntityRequest (ClientRegistrationDTO dto){
        return mapper.map(dto, Client.class);
    }
    private ClientDTO convertToDTO(Client obj){
        return mapper.map(obj, ClientDTO.class);
    }

    private Client convertToEntity (ClientDTO dto){
        return mapper.map(dto, Client.class);
    }

}
