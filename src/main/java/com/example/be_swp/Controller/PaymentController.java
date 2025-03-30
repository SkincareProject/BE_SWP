package com.example.be_swp.Controller;


import com.example.be_swp.DTOs.PaymentDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Payments;
import com.example.be_swp.Repository.PaymentRepository;
import com.example.be_swp.Service.PaymentService;
import jakarta.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
   @Autowired
    private final PaymentService _paymentService;
   @Autowired
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentService paymentService, PaymentRepository paymentRepository){

        _paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/findById/{paymentId}")
    public ApiResponse<PaymentDTO> findById(@PathVariable Long paymentId){
        PaymentDTO paymentDTO = _paymentService.findById(paymentId);

        String status = "";
        String message = "";

        if (paymentDTO.getPaymentId() == -1){
            status = "404";
            message = "Not Found Payment!";
        }else{
            status = "200";
            message = "Get Payment Successfully!";
        }

        return new ApiResponse<>(status,paymentDTO,message);
    }

    @GetMapping("/findByAppointmentId/{appointmentId}")
    public ApiResponse<?> findByAppointmentId(@PathVariable Long appointmentId){
        Payments paymentDTO = paymentRepository.findByAppointmentId(appointmentId);

        String status = "";
        String message = "";

        if (paymentDTO.getPaymentId() == -1){
            status = "404";
            message = "Not Found Payment!";
        }else{
            status = "200";
            message = "Get Payment Successfully!";
        }

        return new ApiResponse<>(status,paymentDTO,message);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ApiResponse<List<?>> findAllByUserId(@PathVariable Long userId){
        List<PaymentDTO> paymentDTOList = paymentRepository.findAllByUserId(userId);

        String status = "";
        String message = "";

        if (paymentDTOList.isEmpty()){
            status = "404";
            message = "Not Found Payment!";
        }else{
            status = "200";
            message = "Get Payment Successfully!";
        }

        return new ApiResponse<>(status,paymentDTOList,message);
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
            message = "Not Found Service Or Customer!";
        } else if (orderUrl.equals("-2")) {
            status = "400";
            message = "This Appointment Already Has A Payment";
        } else{
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
    public ApiResponse<String> refundZaloPay(@PathVariable Long paymentId) throws IOException {
        String refund = _paymentService.refundZaloPay(paymentId);

        String status = "";
        String message = "";

        if (refund.equals("-1")){
            status = "404";
            message = "Payment Not Found";
        }else {
            status = "200";
            message = "Refund Successfully";
        }

        return new ApiResponse<>(status,paymentId+"",message);
    }

}
