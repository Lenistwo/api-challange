package pl.lenistwo.apichallange.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lenistwo.apichallange.model.pagination.Pagination;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse<T> {
    private T data;
    private int status;
    private Pagination pagination;
}
