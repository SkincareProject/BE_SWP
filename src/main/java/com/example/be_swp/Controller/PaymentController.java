package com.example.be_swp.Controller;


import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.PaymentService;
import jakarta.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService _paymentService;

    public PaymentController(PaymentService paymentService){

        _paymentService = paymentService;

    }

    @GetMapping("/success")
    public ApiResponse<String> paymentSuccess(){
        return new ApiResponse<>("200","Payment Complete!","OK");
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
    public ApiResponse<String> createPayment(@RequestParam int userId, @RequestParam int appointmentId) throws Exception {

        String orderUrl = _paymentService.createZaloOrderUrl(userId, appointmentId);

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

    @PostMapping("/callback/zaloPay")
    public String callback(@RequestBody String jsonStr) {
        return _paymentService.callbackZalo(jsonStr);
    }

    @PostMapping("/refund/zaloPay/{paymentId}")
    public ApiResponse<String> refundZaloPay(@PathVariable int paymentId) throws IOException {
        String a = _paymentService.refundZaloPay(paymentId);

        return new ApiResponse<>();
    }

}
