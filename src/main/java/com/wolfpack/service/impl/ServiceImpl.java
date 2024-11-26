package com.wolfpack.service.impl;

import com.wolfpack.exception.InsufficientStockException;
import com.wolfpack.model.Image;
import com.wolfpack.model.Product;
import com.wolfpack.model.Service;
import com.wolfpack.model.ServiceProduct;
import com.wolfpack.repo.IImageRepo;
import com.wolfpack.repo.IProductRepo;
import com.wolfpack.repo.IServiceRepo;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.service.IImageService;
import com.wolfpack.service.IServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl extends CRUDServiceImpl<Service,Integer> implements IServiceService{


    private final IServiceRepo serviceRepo;
    private final IProductRepo productRepo;
    private final IImageRepo imageRepo;
    @Override
    protected IGenericRepo<Service, Integer> getRepo() {
        return serviceRepo;
    }

    @Transactional
    @Override
    public Service saveWithValidation(Service service) throws Exception {

        return getService(service);
    }

    @Override
    public Service updateWithValidation(Service service, Integer id)  throws Exception{
        Service serviceFound = serviceRepo.getReferenceById(id);

        resetQuantityProducts(serviceFound);

        return getService(service);
    }

    private Service getService(Service service) {
        service.getProducts().forEach(sp -> {
            try {
                validateAndUpdateProduct(sp);
            } catch (InsufficientStockException e) {
                throw new InsufficientStockException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Error processing ServiceProduct: " + sp, e);
            }
        });
        return serviceRepo.save(service);
    }

    @Override
    public List<Image> findAllImages() throws Exception{
        return serviceRepo.findAllImages();
    }

    @Override
    public byte[] updateImagenService( Image image, Integer idService) throws Exception{
        image.setIdImage(serviceRepo.findIdImage(idService));
        imageRepo.save(image);
        return image.getData();
    }


    private void validateAndUpdateProduct(ServiceProduct sp) throws Exception {
        Product product = productRepo.getReferenceById(sp.getProduct().getIdProduct());
        validateStock(product, sp.getQuantityProduct());
        updateProductStock(product, sp.getQuantityProduct());
    }

    private void validateStock(Product product, int quantity) {
        if (product.getQuantityAvailable() < quantity) {
            throw new InsufficientStockException("Insufficient stock available for product: " + product.getName());
        }
    }

    private void updateProductStock(Product product, int quantity) throws Exception {
        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
        productRepo.save(product);
    }

    private void resetQuantityProducts(Service service){

        service.getProducts().forEach(sp ->{
            Product product = sp.getProduct();
            int quantity =sp.getQuantityProduct();
            int productQuantity = product.getQuantityAvailable();
            product.setQuantityAvailable(quantity + productQuantity);

            productRepo.save(product);
        });
    }

}
