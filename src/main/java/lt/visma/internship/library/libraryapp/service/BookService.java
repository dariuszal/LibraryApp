package lt.visma.internship.library.libraryapp.service;

import lombok.RequiredArgsConstructor;
import lt.visma.internship.library.libraryapp.model.Book;
import lt.visma.internship.library.libraryapp.repository.BookRepository;
import lt.visma.internship.library.libraryapp.request.BookRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book addNewBook(BookRequest bookRequest) throws IOException {
        Book book = new Book(
                bookRequest.getName(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getLanguage(),
                bookRequest.getPublicationDate(),
                bookRequest.getIsbn()
        );
        bookRepository.save(book);

        return book;
    }

    public Book findByUUID(UUID uuid) throws IOException {
        return bookRepository.findByUUID(uuid);
    }

    public List<Book> getAllBooks() throws IOException {
        return bookRepository.getDatabase();
    }

    public void deleteBook(UUID uuid) throws IOException {
        Book book = bookRepository.findByUUID(uuid);
        if (!book.isAvailable()) {
            throw new IllegalStateException(String.format("Book %s cannot be deleted, because someone is still reading it", book.getUuid()));
        } else {
            bookRepository.delete(uuid);
        }
    }
        //Filter is based on removal form array if criteria not met.
    public List<Book> returnFilteredBooks(String name, String author, String category, String language, String isbn, Boolean available) throws IOException {
        List<Book> filtered = bookRepository.getDatabase();
        System.out.println("name = "+name);
        System.out.println("author = "+author);
        System.out.println("category = "+category);
        System.out.println("language = "+language);
        System.out.println("isbn = "+isbn);
        System.out.println("available = "+available);

        if(name !=null) {
            filtered.removeIf(book -> !book.getName().toLowerCase().contains(name.toLowerCase()));
            System.out.println("namefiltered");
        }
        if(author !=null) {
            filtered.removeIf(book -> !book.getAuthor().toLowerCase().contains(author.toLowerCase()));
            System.out.println("authorfiltered");
        }
        if(category !=null) {
            filtered.removeIf(book -> !book.getCategory().toLowerCase().contains(category.toLowerCase()));
            System.out.println("categoryfiltered");
        }
        if(language !=null) {
            filtered.removeIf(book -> !book.getLanguage().toLowerCase().contains(language.toLowerCase()));
            System.out.println("languagefiltered");

        }
        if(isbn !=null) {
            filtered.removeIf(book -> !book.getIsbn().toLowerCase().contains(isbn.toLowerCase()));
            System.out.println("isbnfiltered");

        }
        if(available != null) {
            filtered.removeIf(book -> !book.isAvailable()==available);
            System.out.println("availablefltered");

        }

        return filtered;
    }
}
