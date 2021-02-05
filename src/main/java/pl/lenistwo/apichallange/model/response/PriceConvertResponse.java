package pl.lenistwo.apichallange.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceConvertResponse {
    private int status;
    private double price;
    private String currency;
}
