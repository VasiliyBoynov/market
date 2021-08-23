package ru.geekbrains.msproducts.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idInfo;

    @Column(name = "url_JPEG")
    private String url_JPEG;

    @Column(name = "url_info")
    private String url_info;

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
