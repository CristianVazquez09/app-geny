package com.wolfpack.controller;

import com.wolfpack.dto.HourAppointmentDTO;
import com.wolfpack.model.HourAppointment;
import com.wolfpack.service.IHourAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hourAppointments")
@RequiredArgsConstructor
public class HourAppointmentController {

    private final IHourAppointmentService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<HourAppointmentDTO>> findAll () throws Exception {
        List<HourAppointmentDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HourAppointmentDTO> findById (@PathVariable("id") Integer id) throws Exception {
        HourAppointment obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody HourAppointmentDTO dto) throws Exception{
        HourAppointment obj = service.save(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getIdHourAppointment()).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HourAppointmentDTO> update(@Valid @RequestBody HourAppointmentDTO dto, @PathVariable("id") Integer id) throws Exception {
        dto.setIdHour(id);
        HourAppointment obj = service.update(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private HourAppointmentDTO convertToDTO(HourAppointment obj){
        return mapper.map(obj, HourAppointmentDTO.class);
    }

    private HourAppointment convertToEntity (HourAppointmentDTO dto){
        return mapper.map(dto, HourAppointment.class);
    }

}
