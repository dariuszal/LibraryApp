package lt.visma.internship.library.libraryapp.repository;

import lt.visma.internship.library.libraryapp.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderRepository {
    void save(Order order) throws IOException;
    List<Order> getDatabase() throws IOException;
}
