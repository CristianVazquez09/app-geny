package com.wolfpack.controller;

import com.wolfpack.dto.ProductDTO;
import com.wolfpack.model.Image;
import com.wolfpack.model.Product;
import com.wolfpack.model.Service;
import com.wolfpack.model.enums.ProductTypeEnum;
import com.wolfpack.service.IImageService;
import com.wolfpack.service.IProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;
    private final IImageService imageService;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll () throws Exception{
        List<ProductDTO>list= service.findAll().stream().map(this::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById (@PathVariable("id") Integer id) throws Exception {
        Product obj = service.findById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @GetMapping(value = "/readImage/{idProduct}")
    public ResponseEntity<Image> readImage(@PathVariable("idProduct") Integer idProduct) throws Exception {

        Image arr = imageService.findByIdProduct(idProduct);

        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    @GetMapping(value = "/readAllImages")
    public ResponseEntity<List<Image>> readAllImages() throws Exception {
        List<Image> list = imageService.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> save(@Valid @RequestPart("product") ProductDTO dto, @RequestPart("image") MultipartFile image) throws Exception {

        Image data = new Image();
        data.setName(image.getOriginalFilename());
        data.setData(image.getBytes());

        Product obj = convertToEntity(dto);
        obj.setImage(data);
        Product productSaved= service.save(obj);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productSaved.getIdProduct()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "updateProduct/{idProduct}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto,@PathVariable("idProduct") Integer id ) throws Exception {
        Product obj = convertToEntity(dto);
        Product objSought = service.findById(id);

        obj.setIdProduct(id);
        obj.setImage(objSought.getImage());

        return ResponseEntity.ok(convertToDTO( service.update(obj,id)));
    }

    @PutMapping(value = "/updateImage/{idProduct}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage (@PathVariable("idProduct") Integer id,@RequestParam("image") MultipartFile image) throws Exception {
        Image data = new Image();
        data.setName(image.getOriginalFilename());
        data.setData(image.getBytes());

        byte[] updateData = service.updateImagenProduct(data,id);


        return ResponseEntity.ok(updateData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ProductDTO convertToDTO(Product obj){
        return mapper.map(obj, ProductDTO.class);
    }

    private Product convertToEntity (ProductDTO dto){
        return mapper.map(dto, Product.class);
    }

}
