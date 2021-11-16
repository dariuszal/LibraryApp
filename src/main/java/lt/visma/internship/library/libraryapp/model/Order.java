package lt.visma.internship.library.libraryapp.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private UUID orderId;
    private String userEmail;
    private List<String> books;
    private LocalDateTime dateTime;
    private LocalDate returnDate;

    public Order(String userEmail, List<String> books, LocalDate returnDate) {
        this.orderId = UUID.randomUUID();
        this.userEmail = userEmail;
        this.books = books;
        this.dateTime = LocalDateTime.now();
        this.returnDate = returnDate;

    }
}
