package lt.visma.internship.library.libraryapp.service;

import lombok.RequiredArgsConstructor;
import lt.visma.internship.library.libraryapp.model.Book;
import lt.visma.internship.library.libraryapp.model.Order;
import lt.visma.internship.library.libraryapp.repository.BookRepository;
import lt.visma.internship.library.libraryapp.repository.OrderRepository;
import lt.visma.internship.library.libraryapp.repository.UserRepository;
import lt.visma.internship.library.libraryapp.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void createNewOrder(OrderRequest orderRequest) throws IOException {
        LocalDate returnLatest = LocalDate.now().plusMonths(2);
        if(orderRequest.getReturnDate().isAfter(returnLatest)) {
            throw new IllegalArgumentException("Return date must be not more than 2 months");
        }
        List<String> books = new ArrayList<>();
        var user = userRepository.findByEmail(orderRequest.getUserEmail()).orElseThrow();

        if(orderRequest.getBooks().size()+user.getBooks().size() >3 ) {
            throw new IllegalArgumentException("You can have maximum 3 books");
        }

        for(String bookId: orderRequest.getBooks()){
            Book book = bookRepository.findByUUID(UUID.fromString(bookId));
            if(!book.isAvailable()) {
                throw new IllegalStateException(String.format("Book %s is not available",book.getName()));
            }
            book.setAvailable(false);
            book.setTakenBy(user.getId());
            books.add(book.getName());
            user.getBooks().add(book.getUuid());

            bookRepository.delete(book.getUuid());
            bookRepository.save(book);
        }
        //delete entry with wrong books[] count.
        userRepository.delete(user.getEmail());
        userRepository.save(user);

        Order order = new Order(
                user.getEmail(),
                books,
                orderRequest.getReturnDate()
        );
        orderRepository.save(order);
    }
}
