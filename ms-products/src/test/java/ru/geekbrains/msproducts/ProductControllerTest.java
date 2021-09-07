package ru.geekbrains.msproducts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.geekbrains.msproducts.model.entities.Category;
import ru.geekbrains.msproducts.model.entities.Product;
import ru.geekbrains.msproducts.repositories.CategoryRepository;
import ru.geekbrains.msproducts.repositories.ProductRepository;
import ru.geekbrains.msproducts.services.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllProductsTest() throws Exception {
        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(4)))
                .andExpect(jsonPath("$.content[0].name", is("хлеб")));
    }

    @Test
    public void getProductsByIdTest()throws Exception{
        mvc.perform(get("/products/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("box")));
    }

    @Test
    public void getProductsByIdExceptionTest()throws Exception{
        mvc.perform(get("/products/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void postSaveProduct()throws Exception{
        Category cat = categoryRepository.findByIdCategory(1l);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(cat);

        Product test1 = new Product();
        test1.setIdProduct(1L);
        test1.setName("key");
        test1.setPrice(100.0f);
        test1.setCategories(categoryList);
        test1.setDataUpdate(new Date());


        mvc.perform(put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(test1)))
                //.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("key"));

        int count = productService.deleteById(5L).size();
        System.out.println("Количество записей:" + count);


    }
}
