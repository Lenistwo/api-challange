package pl.lenistwo.apichallange.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lenistwo.apichallange.model.product.Currency;
import pl.lenistwo.apichallange.model.request.CurrencyRequest;
import pl.lenistwo.apichallange.model.response.CurrencyResponse;
import pl.lenistwo.apichallange.model.response.PageableResponse;
import pl.lenistwo.apichallange.model.response.PriceConvertResponse;
import pl.lenistwo.apichallange.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public PageableResponse<List<Currency>> findAllCurrencies(@RequestParam(defaultValue = "0", required = false) int page,
                                                              @RequestParam(defaultValue = "5", required = false) int pageSize){
        return currencyService.getAllCurrencies(page, pageSize);
    }

    @GetMapping("/{currencyId}")
    public CurrencyResponse findCurrencyById(@PathVariable long currencyId){
        return currencyService.getCurrencyById(currencyId);
    }

    @GetMapping("/price")
    public PriceConvertResponse toPrice(@RequestParam double price, @RequestParam String toCurrency){
        return currencyService.convertPrice(price, toCurrency);
    }

    @PostMapping
    public CurrencyResponse createCurrency(@RequestBody CurrencyRequest currencyRequest){
        return currencyService.createCurrency(currencyRequest);
    }

    @PutMapping
    public CurrencyResponse updateCurrency(@RequestBody Currency currency){
        return currencyService.updateCurrency(currency);
    }

    @DeleteMapping("/{currencyId}")
    public ResponseEntity<Void> deleteCurrencyById(@PathVariable long currencyId){
        return currencyService.deleteCurrency(currencyId);
    }
}

