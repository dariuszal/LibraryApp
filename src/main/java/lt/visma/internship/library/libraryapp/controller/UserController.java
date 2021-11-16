package lt.visma.internship.library.libraryapp.controller;

import lombok.RequiredArgsConstructor;
import lt.visma.internship.library.libraryapp.model.User;
import lt.visma.internship.library.libraryapp.request.UserRequest;
import lt.visma.internship.library.libraryapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v0.1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User addNewUser(@RequestBody UserRequest userRequest) throws IOException {
        return userService.addNewUser(userRequest);
    }
    @GetMapping
    public User findByEmail(@RequestParam String email) throws IOException {
        return userService.findByEmail(email);
    }

}
