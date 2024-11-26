package com.wolfpack.controller;

import com.wolfpack.dto.SaleDTO;
import com.wolfpack.model.Sale;
import com.wolfpack.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<SaleDTO>> findAll () throws Exception {
        List<SaleDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Sale obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.saveWithValidation(convertToEntity(dto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getIdSale()).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto, @PathVariable("id") Integer id) throws Exception {
        dto.setIdSale(id);
        Sale obj = service.updateWithValidation(convertToEntity(dto),id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private SaleDTO convertToDTO(Sale obj){
        return mapper.map(obj, SaleDTO.class);
    }

    private Sale convertToEntity (SaleDTO dto){
        return mapper.map(dto, Sale.class);
    }

}
