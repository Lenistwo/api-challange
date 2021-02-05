package pl.lenistwo.apichallange.model.request;

import lombok.Data;

@Data
public class CurrencyRequest {
    private String code;
    private double course;
}
