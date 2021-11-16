package lt.visma.internship.library.libraryapp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lt.visma.internship.library.libraryapp.model.User;
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
import java.util.Optional;

@Service
public class UserRepositoryImplementation implements UserRepository {
    private final String FILE_NAME = "users.json";

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    List<User> users = new ArrayList<>();

    @Override
    public void save(User user) throws IOException {
        users = checkDatabase(getDatabase());
        users.add(user);
        Writer writer = new FileWriter(FILE_NAME);
        gson.toJson(users, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public List<User> getDatabase() throws IOException {
        Reader reader = checkReader();
        return new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
    }

    @Override
    public Optional<User> findByEmail(String email) throws IOException {
        return checkDatabase(getDatabase()).stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public void delete(String email) throws IOException {
        users = checkDatabase(getDatabase());
        users.removeIf(user -> user.getEmail().equals(email));
        Writer writer = new FileWriter(FILE_NAME);
        gson.toJson(users, writer);
        writer.flush();
        writer.close();
    }

    //If the file is empty it will create new arrayList.
    public List<User> checkDatabase(List<User> users) {
        if(users == null) {
            return new ArrayList<>();
        }
        else return users;
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
