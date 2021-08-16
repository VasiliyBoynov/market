package ru.geekbrains.msproducts.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.msproducts.model.entities.Product;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long idProduct;
    private String name;
    private float price;
    private Date time_update;
    private List<String> categoryName;

    public ProductDto(Product p) {
        this.idProduct = p.getIdProduct();
        this.name = p.getName();
        this.price = p.getPrice()*((float)(1f-p.getCategories().stream().map(cat -> cat.getSeal()).max(Integer::compareTo).get()/100f));
        this.time_update = p.getDataUpdate();
        this.categoryName = p.getCategories().stream().map(cat -> cat.getName()).collect(Collectors.toUnmodifiableList());
    }
}
