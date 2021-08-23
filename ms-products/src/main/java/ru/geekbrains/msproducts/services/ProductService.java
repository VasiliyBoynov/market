package ru.geekbrains.msproducts.services;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.msproducts.model.dtos.ProductDto;
import ru.geekbrains.msproducts.model.entities.Product;
import ru.geekbrains.msproducts.repositories.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(p -> new ProductDto(p));
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(p -> new ProductDto(p));
    }

    public Product save(Product product){
        return  productRepository.save(product);
    }

    public List<Product> deleteById(Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }















}
