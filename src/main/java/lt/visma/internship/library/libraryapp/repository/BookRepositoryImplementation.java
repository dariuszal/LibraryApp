package lt.visma.internship.library.libraryapp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lt.visma.internship.library.libraryapp.model.Book;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookRepositoryImplementation implements BookRepository {
    private final String FILE_NAME = "books.json";
    List<Book> books = new ArrayList<>();

    public BookRepositoryImplementation() throws IOException {
    }

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void save(Book book) throws IOException {
            books = checkDatabase(getDatabase());
            books.add(book);
            Writer writer = new FileWriter(FILE_NAME);
            gson.toJson(books, writer);
            writer.flush();
            writer.close();
    }
    @Override
    public List<Book> getDatabase() throws IOException {
        Reader reader = checkReader();
        return new Gson().fromJson(reader, new TypeToken<List<Book>>() {}.getType());
    }

    @Override
    public Book findByUUID(UUID uuid) throws IOException {
        for(Book book: checkDatabase(getDatabase())) {
            if(book.getUuid().equals(uuid)) {
                return book;
            }
        }
        return null;
    }
    @Override
    public void delete(UUID uuid) throws IOException {
        books = checkDatabase(getDatabase());
        books.removeIf(book -> book.getUuid().equals(uuid));
        Writer writer = new FileWriter(FILE_NAME);
        gson.toJson(books, writer);
        writer.flush();
        writer.close();
    }

    //If the file is empty it will create new arrayList.
    public List<Book> checkDatabase(List<Book> books) {
        if(books == null) {
            return new ArrayList<>();
        }
        else return books;
    }
    //If file does not exist, will create new file and return the reader.
    public Reader checkReader() throws IOException {
        try {
            return Files.newBufferedReader(Paths.get(FILE_NAME));
        } catch (NoSuchFileException e) {
            Writer writer = new FileWriter(FILE_NAME);
            return Files.newBufferedReader(Paths.get(FILE_NAME));
        }
    }
}
