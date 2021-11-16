package lt.visma.internship.library.libraryapp.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequest {
    private String userEmail;
    private List<String> books;
    private LocalDate returnDate;
}
