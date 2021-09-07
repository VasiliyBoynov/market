package ru.geekbrains.msproducts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.msproducts.model.entities.Category;


public interface CategoryRepository  extends JpaRepository<Category,Long> {
    public Category findByIdCategory(Long id);
}
