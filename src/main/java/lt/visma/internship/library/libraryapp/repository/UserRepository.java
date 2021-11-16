package lt.visma.internship.library.libraryapp.repository;

import lt.visma.internship.library.libraryapp.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user) throws IOException;
    List<User> getDatabase() throws IOException;
    Optional<User> findByEmail(String email) throws IOException;
    void delete(String email)throws IOException;
}
