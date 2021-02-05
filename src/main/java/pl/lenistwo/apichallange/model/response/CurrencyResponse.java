package pl.lenistwo.apichallange.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lenistwo.apichallange.model.product.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {
    private int status;
    private Currency currency;
}
