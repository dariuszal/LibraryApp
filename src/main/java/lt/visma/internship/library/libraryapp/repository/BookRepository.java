package lt.visma.internship.library.libraryapp.repository;

import lt.visma.internship.library.libraryapp.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface BookRepository {
    void save(Book book) throws IOException;
    List<Book> getDatabase() throws IOException;
    Book findByUUID(UUID uuid) throws IOException;
    void delete(UUID uuid)throws IOException;

}
