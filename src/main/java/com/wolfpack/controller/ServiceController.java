package com.wolfpack.controller;

import com.wolfpack.dto.ServiceDTO;
import com.wolfpack.model.Image;
import com.wolfpack.model.Service;
import com.wolfpack.service.IImageService;
import com.wolfpack.service.IServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final IServiceService service;
    private final IImageService imageService;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<ServiceDTO>> findAll () throws Exception{
        List<ServiceDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Service obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @GetMapping(value = "/readImage/{idService}")
    public ResponseEntity<Image> readImage(@PathVariable("idService") Integer idService) throws Exception {

        Image arr = imageService.findByIdService(idService);

        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    @GetMapping(value = "/readAllImages")
    public ResponseEntity<List<Image>> readAllImages() throws Exception {
        List<Image> list = service.findAllImages();
        return ResponseEntity.ok(list);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> save(@RequestPart("image") MultipartFile image, @Valid @RequestPart("service") ServiceDTO dto) throws Exception{

        Image data = new Image();
        data.setName(image.getOriginalFilename());
        data.setData(image.getBytes());

        Service obj = convertToEntity(dto);
        obj.setImage(data);
        Service serviceSaved=service.saveWithValidation(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(serviceSaved.getIdService()).toUri();
        return  ResponseEntity.created(location).build();
    }

    @PutMapping(value = "updateService/{idService}")
    public ResponseEntity<ServiceDTO> update(@Valid @RequestBody ServiceDTO dto,@PathVariable("idService") Integer id ) throws Exception {
        Service obj = convertToEntity(dto);
        Service objSought = service.findById(id);

        obj.setIdService(id);
        obj.setImage(objSought.getImage());

        return ResponseEntity.ok(convertToDTO( service.updateWithValidation(obj,id)));
    }

    @PutMapping(value = "/updateImage/{idService}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage (@PathVariable("idService") Integer idService,@RequestParam("image") MultipartFile image) throws Exception {
        Image data = new Image();
        data.setName(image.getOriginalFilename());
        data.setData(image.getBytes());

        byte[] updateData=service.updateImagenService(data,idService);


        return ResponseEntity.ok(updateData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id")Integer id )throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ServiceDTO convertToDTO(Service obj){
        return mapper.map(obj, ServiceDTO.class);
    }

    private Service convertToEntity (ServiceDTO dto){
        return mapper.map(dto, Service.class);
    }

}
