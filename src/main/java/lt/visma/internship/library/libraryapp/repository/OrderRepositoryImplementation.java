package lt.visma.internship.library.libraryapp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lt.visma.internship.library.libraryapp.model.Order;
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

@Service
public class OrderRepositoryImplementation implements OrderRepository {

    private final String FILE_NAME = "orders.json";
    List<Order> orders = new ArrayList<>();

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void save(Order order) throws IOException {
        orders = checkDatabase(getDatabase());
        orders.add(order);
        Writer writer = new FileWriter(FILE_NAME);
        gson.toJson(orders, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public List<Order> getDatabase() throws IOException {
        Reader reader = checkReader();
        return new Gson().fromJson(reader, new TypeToken<List<Order>>() {}.getType());
    }

    public List<Order> checkDatabase(List<Order> orders) {
        if (orders == null) {
            return new ArrayList<>();
        } else return orders;
    }

    public Reader checkReader() throws IOException {
        try {
            return Files.newBufferedReader(Paths.get(FILE_NAME));
        } catch (NoSuchFileException e) {
            Writer writer = new FileWriter(FILE_NAME);
            return Files.newBufferedReader(Paths.get(FILE_NAME));
        }
    }
}
