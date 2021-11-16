package lt.visma.internship.library.libraryapp.service;

import lt.visma.internship.library.libraryapp.model.User;
import lt.visma.internship.library.libraryapp.repository.UserRepository;
import lt.visma.internship.library.libraryapp.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(UserRequest userRequest) throws IOException {
        User user = new User(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail()
        );
        userRepository.save(user);
        return user;
    }

    public User findByEmail(String email) throws IOException {
        return userRepository.findByEmail(email).orElseThrow();
    }

}
