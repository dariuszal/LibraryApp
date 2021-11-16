package lt.visma.internship.library.libraryapp.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Book {
    private String name;
    private String author;
    private String category;
    private String language;
    private LocalDate publicationDate;
    private String isbn;
    private UUID uuid;
    private boolean isAvailable;
    private UUID takenBy;

    public Book(String name, String author,String category, String language, LocalDate publicationDate, String isbn) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.uuid = UUID.randomUUID();
        this.isAvailable = true;
    }

    public Book() {
    }
}


