package lt.visma.internship.library.libraryapp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UUID> books = new ArrayList<>();

    public User(String firstName, String lastName, String email) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

