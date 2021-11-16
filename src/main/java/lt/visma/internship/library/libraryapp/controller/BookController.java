package lt.visma.internship.library.libraryapp.controller;

import lombok.RequiredArgsConstructor;
import lt.visma.internship.library.libraryapp.model.Book;
import lt.visma.internship.library.libraryapp.request.BookRequest;
import lt.visma.internship.library.libraryapp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v0.1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book addNewBook(@RequestBody BookRequest bookRequest) throws IOException {
        return bookService.addNewBook(bookRequest);
    }

    @GetMapping
    public Book findByUUID(@RequestParam UUID uuid) throws IOException {
        return bookService.findByUUID(uuid);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() throws IOException {
        return bookService.getAllBooks();
    }

    //Cannot delete book if someone is still reading it.
    @DeleteMapping("delete/{uuid}")
    public void deleteByUUID(@PathVariable UUID uuid) throws IOException {
        bookService.deleteBook(uuid);
    }

    @GetMapping("/filter")
    public List<Book> filteredBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Boolean available
            ) throws IOException {
        return bookService.returnFilteredBooks(name,author,category,language,isbn,available);
    }
}
