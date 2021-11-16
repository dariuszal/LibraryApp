package lt.visma.internship.library.libraryapp.controller;

import lombok.RequiredArgsConstructor;
import lt.visma.internship.library.libraryapp.request.OrderRequest;
import lt.visma.internship.library.libraryapp.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v0.1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createNewOrder(@RequestBody OrderRequest orderRequest) throws IOException {
        orderService.createNewOrder(orderRequest);
    }
}
