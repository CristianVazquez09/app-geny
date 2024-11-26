package com.wolfpack.controller;

import com.wolfpack.dto.AppointmentDTO;
import com.wolfpack.model.Appointment;
import com.wolfpack.service.IAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll () throws Exception {
        List<AppointmentDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Appointment obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AppointmentDTO dto) throws Exception{
        Appointment obj = service.saveAppointmentWithValidation(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getIdAppointment()).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@Valid @RequestBody AppointmentDTO dto, @PathVariable("id") Integer id) throws Exception {
        dto.setIdAppointment(id);
        Appointment obj = service.update(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private AppointmentDTO convertToDTO(Appointment obj){
        return mapper.map(obj, AppointmentDTO.class);
    }

    private Appointment convertToEntity (AppointmentDTO dto){
        return mapper.map(dto, Appointment.class);
    }

}
