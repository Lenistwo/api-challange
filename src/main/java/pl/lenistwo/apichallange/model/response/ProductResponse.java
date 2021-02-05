package pl.lenistwo.apichallange.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lenistwo.apichallange.model.product.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int statusCode;
    private Product product;
}
