package lt.visma.internship.library.libraryapp.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BookRequest {
    private String name;
    private String author;
    private String category;
    private String language;
    private LocalDate publicationDate;
    private String isbn;
}
