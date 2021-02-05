package pl.lenistwo.apichallange.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rate;
    @ManyToOne
    private Product product;

    public Rating(int rate, Product product) {
        this.rate = rate;
        this.product = product;
    }
}
