package ru.geekbrains.msproducts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.msproducts.model.dtos.ProductDto;
import ru.geekbrains.msproducts.model.entities.Category;
import ru.geekbrains.msproducts.model.entities.Product;
import ru.geekbrains.msproducts.repositories.ProductRepository;
import ru.geekbrains.msproducts.services.ProductService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {

    private Map<Long, Product> productList = new HashMap<>();

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void init(){

        Category cat = new Category();
        cat.setIdCategory(1L);
        cat.setName("testSeal");
        cat.setSeal(10);

        Category cat1 = new Category();
        cat1.setIdCategory(2L);
        cat1.setName("testNoSeal");
        cat1.setSeal(0);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(cat);

        List<Category> categoryList1 = new ArrayList<>();
        categoryList1.add(cat1);

        Product test1 = new Product();
        test1.setIdProduct(1L);
        test1.setName("клюшка");
        test1.setPrice(100.0f);
        test1.setCategories(categoryList);
        test1.setDataUpdate(new Date());


        Product test2 = new Product();
        test2.setIdProduct(2L);
        test2.setName("шайба");
        test2.setPrice(100.0f);
        test2.setCategories(categoryList1);
        test2.setDataUpdate(new Date());

       /* Product test3 = new Product();
        test3.setIdProduct(3L);
        test3.setName("ворота");
        test3.setPrice(112233.56f);
        test3.setDataUpdate(new Date());
        test3.setCategories(new ArrayList<>());


        Product test4 = new Product();
        test4.setIdProduct(4L);
        test4.setName("сетка");
        test4.setPrice(1111.56f);
        test4.setDataUpdate(new Date());
        test4.setCategories(new ArrayList<>());*/

        productList.put(1L,test1);
        productList.put(2L,test2);
        //productList.put(3L,test3);
        //productList.put(4L,test4);

        Mockito
                .doReturn(Optional.of(productList.get(1L)))
                .when(productRepository)
                .findById(1L);
        Mockito
                .doReturn(Optional.of(productList.get(2L)))
                .when(productRepository)
                .findById(2L);
        /*Mockito
                .doReturn((productList.get(3L)))
                .when(productRepository)
                .findById(3L);
        Mockito
                .doReturn((productList.get(4L)))
                .when(productRepository)
                .findById(4L);*/
    }
    @Test
    public void findById1LShouldBeStick(){
        ProductDto p = productService.findById(1L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(productList.get(1L).getName(), p.getName());
    }
    @Test
    public void findById1LCostShouldBe90(){
        ProductDto p = productService.findById(1L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(90f, p.getPrice());
    }

    @Test
    public void findById2LCostShouldBe100(){
        ProductDto p = productService.findById(2L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(2L));
        Assertions.assertEquals(100f, p.getPrice());
    }
}
