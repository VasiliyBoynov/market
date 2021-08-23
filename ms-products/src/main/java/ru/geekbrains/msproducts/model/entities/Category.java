package ru.geekbrains.msproducts.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "seal")
    private int seal;

    @Column(name="dataCreate")
    @CreationTimestamp
    private Date dataCreate;

    @Column(name="dataUpdate")
    @UpdateTimestamp
    private Date dataUpdate;

    @Column(name="dataDelete")
    @UpdateTimestamp
    private Date dataDelete;

}
