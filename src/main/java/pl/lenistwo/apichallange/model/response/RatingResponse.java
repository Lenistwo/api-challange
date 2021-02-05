package pl.lenistwo.apichallange.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private int statusCode;
    private List<Integer> rating;
}
