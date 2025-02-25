package com.example.be_swp.Service;

import com.example.be_swp.Models.Services;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.PaymentRepository;
import com.example.be_swp.Repository.ServicesRepository;
import com.example.be_swp.Repository.UsersRepository;
import com.example.be_swp.Util.HMACUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentService {

    private final PaymentRepository _paymentRepository;
    private final UsersRepository _usersRepository;
    private final ServicesRepository _servicesRepository;

    public PaymentService(PaymentRepository _paymentRepository, UsersRepository _usersRepository, ServicesRepository _servicesRepository) {
        this._paymentRepository = _paymentRepository;
        this._usersRepository = _usersRepository;
        this._servicesRepository = _servicesRepository;
    }

    private Map<String, String> config = new HashMap<String, String>(){{
//        put("appid", "2554");
//        put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
//        put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
        put("appid", "554");
        put("key1", "8NdU5pG5R2spGHGhyO99HN1OhD8IQJBn");
        put("key2", "uUfsWgfLkRLzq6W2uNXTCxrfxs51auny");
        put("endpoint", "https://sandbox.zalopay.com.vn/v001/tpe/createorder");
    }};

    public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public String createOrderUrl(int userId, int serviceId) throws Exception{

        Optional<Users> optionalUsers = _usersRepository.findById(userId);
        Optional<Services> optionalServices = _servicesRepository.findById(serviceId);

        Users users = new Users();
        Services services = new Services();

        if (optionalServices.isEmpty() || optionalUsers.isEmpty()){
            return "-1";
        }else if (optionalUsers.get().getRoles().getId() != 2) {
            return "-1";
        } else{
            users = optionalUsers.get();
            services = optionalServices.get();
        }


        final Map embeddata = new HashMap(){{
            put("merchantinfo", "embeddata123");
        }};

        Services finalServices = services;
        final Map[] service = {
                new HashMap<String, Object>(){{
                    put("serviceId", finalServices.getServiceId());
                    put("serviceName", finalServices.getServiceName());
                    put("serviceDescription", finalServices.getDescription());
                    put("servicePrice", finalServices.getPrice());
                    put("serviceDuration", finalServices.getDuration());
                }}
        };

        Users finalCustomer = users;
        final Map[] customer = {
                new HashMap<String, Object>(){{
                    put("customerId", finalCustomer.getId());
                    put("customerFullName", finalCustomer.getFullName());
                    put("customerEmail", finalCustomer.getEmail());
                    put("customerPhone", finalCustomer.getPhone());
                }}
        };

        JSONArray jsonArrayCustomer = new JSONArray();
        for (Map map: customer){
            JSONObject jsonObject = new JSONObject(map);
            jsonArrayCustomer.put(jsonObject);
        }

        JSONArray jsonArrayService = new JSONArray();
        for (Map map: service){
            JSONObject jsonObject = new JSONObject(map);
            jsonArrayService.put(jsonObject);
        }


        Map<String, Object> order = new HashMap<String, Object>(){{
            put("appid", config.get("appid"));
            put("apptransid", getCurrentTimeString("yyMMdd") +"_"+ UUID.randomUUID()); // mã giao dich có định dạng yyMMdd_xxxx
            put("apptime", System.currentTimeMillis()); // miliseconds
            put("appuser", jsonArrayCustomer.toString());
            put("amount", (int)finalServices.getPrice());
            put("description", "ZaloPay Intergration Demo");
            put("bankcode", "");
            put("item", jsonArrayService.toString());
            put("embeddata", new JSONObject(embeddata).toString());
        }};

        // appid +”|”+ apptransid +”|”+ appuser +”|”+ amount +"|" + apptime +”|”+ embeddata +"|" +item
        String data = order.get("appid") +"|"+ order.get("apptransid") +"|"+ order.get("appuser") +"|"+ order.get("amount")
                +"|"+ order.get("apptime") +"|"+ order.get("embeddata") +"|"+ order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.get("endpoint"));

        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, Object> e : order.entrySet()) {
            params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
        }

        // Content-Type: application/x-www-form-urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        for (String key : result.keySet()) {
            System.out.format("%s = %s\n", key, result.get(key));
        }

        String url = result.getString("orderurl");

//        System.out.println(result.get("zptranstoken"));
//        System.out.println(order.toString());
//        System.out.println(HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));
//        System.out.println("URL: ");
//        System.out.println(url);

        return url;
    }

}
