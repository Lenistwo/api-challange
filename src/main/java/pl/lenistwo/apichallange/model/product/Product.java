package pl.lenistwo.apichallange.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double price;
    @OneToMany(targetEntity = Rating.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Rating> rating = new ArrayList<>();

    public Product(String name, String description, double price, List<Rating> rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }
}
