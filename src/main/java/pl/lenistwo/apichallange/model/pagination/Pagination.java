package pl.lenistwo.apichallange.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private int page;
    private long pageSize;
    private long totalSize;
    private int totalPages;
}
