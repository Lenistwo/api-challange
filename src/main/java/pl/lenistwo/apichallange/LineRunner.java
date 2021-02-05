package pl.lenistwo.apichallange;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lenistwo.apichallange.model.product.Currency;
import pl.lenistwo.apichallange.model.product.Product;
import pl.lenistwo.apichallange.model.product.Rating;
import pl.lenistwo.apichallange.repository.CurrencyRepository;
import pl.lenistwo.apichallange.repository.ProductRepository;

import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class LineRunner implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {
        var sek = new Currency();
        var sekCode = "sek";
        sek.setCode(sekCode);
        sek.setCourse(0.43);
        currencyRepository.save(sek);

        var nek = new Currency();
        var nekCode = "sek";
        nek.setCode(nekCode);
        nek.setCourse(0.43);
        currencyRepository.save(nek);

        var product = new Product();
        product.setPrice(10.0);
        product.setDescription("ELKO");
        product.setName("aaa");
        var ratings = Arrays.asList(new Rating(1, product), new Rating(2, product));
        product.setRating(ratings);
        productRepository.save(product);
        System.out.println(productRepository.findRateByProductId(1));
    }
}
