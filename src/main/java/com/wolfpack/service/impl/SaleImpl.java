package com.wolfpack.service.impl;

import com.wolfpack.exception.InsufficientStockException;
import com.wolfpack.model.*;
import com.wolfpack.repo.IProductRepo;
import com.wolfpack.repo.ISaleRepo;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SaleImpl extends CRUDServiceImpl<Sale,Integer> implements ISaleService{


    private final ISaleRepo repo;
    private final IProductRepo productRepo;


    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Sale saveWithValidation(Sale sale) throws Exception {
        return getService(sale); // Save the service and the relationship
    }

    @Override
    public Sale updateWithValidation(Sale sale, Integer id)  throws Exception{
        Sale serviceFound = repo.getReferenceById(id);

        resetQuantityProducts(serviceFound);

        return getService(sale);
    }

    private Sale getService(Sale sale) {
        sale.getSaleDetails().forEach(sp -> {
            try {
                validateAndUpdateProduct(sp);
            } catch (InsufficientStockException e) {
                throw new InsufficientStockException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Error processing ServiceProduct: " + sp, e);
            }
        });
        return repo.save(sale);
    }

    private void validateAndUpdateProduct(SaleDetail sp) throws Exception {
        Product product = productRepo.getReferenceById(sp.getProduct().getIdProduct());
        validateStock(product, sp.getQuantity());
        updateProductStock(product, sp.getQuantity());
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

    private void resetQuantityProducts(Sale sale){

        sale.getSaleDetails().forEach(sp ->{
            Product product = sp.getProduct();
            int quantity =sp.getQuantity();
            int productQuantity = product.getQuantityAvailable();
            product.setQuantityAvailable(quantity + productQuantity);

            productRepo.save(product);
        });
    }

}

