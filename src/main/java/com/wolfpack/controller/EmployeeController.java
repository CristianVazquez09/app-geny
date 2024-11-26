package com.wolfpack.controller;

import com.wolfpack.dto.EmployeeRegistrationDTO;
import com.wolfpack.dto.EmployeeDTO;
import com.wolfpack.model.Employee;
import com.wolfpack.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll () throws Exception {
        List<EmployeeDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Employee obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EmployeeRegistrationDTO dto) throws Exception{

        Employee obj = service.saveWithEncryptedPassword(convertToEntityRequest(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getIdEmployee()).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO dto, @PathVariable("id") Integer id) throws Exception {
        dto.setIdEmployee(id);
        Employee obj = service.updateWithExistingPassword(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private Employee convertToEntityRequest (EmployeeRegistrationDTO dto){
        return mapper.map(dto, Employee.class);
    }

    private EmployeeDTO convertToDTO(Employee obj){
        return mapper.map(obj, EmployeeDTO.class);
    }

    private Employee convertToEntity(EmployeeDTO dto){
        return mapper.map(dto, Employee.class);
    }

}
