package ru.geekbrains.msproducts.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name="dataCreate")
    @CreationTimestamp
    private Date dataCreate;

    @Column(name="dataUpdate")
    @UpdateTimestamp
    private Date dataUpdate;

    @Column(name="dataDelete")
    @UpdateTimestamp
    private Date dataDelete;

    @OneToOne
    @JoinColumn(name = "idInfo")
    private Info info;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_category",
            joinColumns = @JoinColumn(name = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idCategory")
    )
    private List<Category> categories;

    //private Integer seal = categories.stream().map(cat->cat.getSeal()).max(Integer::compareTo).get();


}
