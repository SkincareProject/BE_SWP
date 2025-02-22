package com.example.be_swp.Controller;


import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService _paymentService;

    public PaymentController(PaymentService paymentService){

        _paymentService = paymentService;

    }

    @GetMapping
    public ApiResponse<String> get(){

        return new ApiResponse<>("This is","Dummy","Api");
    }
    @PostMapping
    public ApiResponse<String> post(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @PutMapping
    public ApiResponse<String> put(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @DeleteMapping
    public ApiResponse<String> delete(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @GetMapping("/create/zaloPay")
    public ApiResponse<String> createPayment(@RequestParam int userId, @RequestParam int serviceId) throws Exception {

        String orderUrl = _paymentService.createOrderUrl(userId, serviceId);

        String status = "";
        String message = "";

        if (orderUrl.equals("-1")){
            status = "404";
            message = "Not Found Service Or User!";
        }else{
            status = "200";
            message = "Get Order URL Successfully!";
        }

        return new ApiResponse<>(status,orderUrl,message);
    }


}
