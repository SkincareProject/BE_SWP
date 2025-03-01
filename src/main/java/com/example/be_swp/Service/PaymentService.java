package com.example.be_swp.Service;

import com.example.be_swp.Models.Appointments;
import com.example.be_swp.Models.Services;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.AppointmentRepository;
import com.example.be_swp.Repository.PaymentRepository;
import com.example.be_swp.Repository.ServicesRepository;
import com.example.be_swp.Repository.UsersRepository;
import com.example.be_swp.Util.HMACUtil;
import jakarta.xml.bind.DatatypeConverter;
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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class PaymentService {

    private final PaymentRepository _paymentRepository;
    private final UsersRepository _usersRepository;
    private final ServicesRepository _servicesRepository;
    private final AppointmentRepository _appointmentRepository;

    public PaymentService(PaymentRepository _paymentRepository, UsersRepository _usersRepository, ServicesRepository _servicesRepository, AppointmentRepository _appointmentRepository) {
        this._paymentRepository = _paymentRepository;
        this._usersRepository = _usersRepository;
        this._servicesRepository = _servicesRepository;
        this._appointmentRepository = _appointmentRepository;
    }

    private Map<String, String> config = new HashMap<String, String>(){{
        put("app_id", "2554");
        put("key1", "sdngKKJmqEMzvh5QQcdD2A9XBSKUNaYn");
        put("key2", "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf");
        put("endpoint", "https://sb-openapi.zalopay.vn/v2/create");
    }};

    public String getCurrentTimeString(String format) {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(cal);
        return fmt.format(cal.getTimeInMillis());
    }

    public String createZaloOrderUrl(int userId, int appointmentId) throws Exception{

        Optional<Users> optionalUsers = _usersRepository.findById(userId);
        Optional<Appointments> optionalAppointments = _appointmentRepository.findById(appointmentId);

        Users users = new Users();
        Services services = new Services();
        Appointments appointments = new Appointments();

        if (optionalAppointments.isEmpty() || optionalUsers.isEmpty()){
            return "-1";
        }else if (optionalUsers.get().getRoles().getId() != 2) {
            return "-1";
        } else{
            users = optionalUsers.get();
            appointments = optionalAppointments.get();
        }

        services = appointments.getServices();

        Users finalCustomer = users;
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

        JSONArray jsonArrayService = new JSONArray();
        for (Map map: service){
            JSONObject jsonObject = new JSONObject(map);
            jsonArrayService.put(jsonObject);
        }


        Random rand = new Random();
        int random_id = rand.nextInt(1000000);

        final Map embed_data = new HashMap<String,Object>(){{
            put("redirecturl","http://3.26.7.116:3000");
            put("paymentId",1);
        }};

        Map<String, Object> order = new HashMap<String, Object>(){{
            put("app_id", config.get("app_id"));
            put("app_trans_id", getCurrentTimeString("yyMMdd") +"_"+ random_id);
            put("app_time", System.currentTimeMillis()); // miliseconds
            put("app_user", "CustomerID:" + finalCustomer.getId());
            put("amount", (long)finalServices.getPrice());
            put("description", "Beauty Booking - Payment for the order #"+random_id);
            put("bank_code", "");
            put("item", jsonArrayService);
            put("embed_data", new JSONObject(embed_data).toString());
        }};

        // app_id +”|”+ app_trans_id +”|”+ appuser +”|”+ amount +"|" + app_time +”|”+ embed_data +"|" +item
        String data = order.get("app_id") +"|"+ order.get("app_trans_id") +"|"+ order.get("app_user") +"|"+ order.get("amount")
                +"|"+ order.get("app_time") +"|"+ order.get("embed_data") +"|"+ order.get("item");
        order.put("mac", HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, config.get("key1"), data));
        order.put("callback_url","https://e6d1-118-69-182-149.ngrok-free.app/api/payments/callback/zaloPay");

        System.out.println(order.toString());

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

        return result.getString("order_url");
    }

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String key2 = "trMrHtvjo6myautxDUiAcYsVtaeQ8nhf";
    private Mac HmacSHA256;

    public void CallbackController() throws Exception  {
        HmacSHA256 = Mac.getInstance("HmacSHA256");
        HmacSHA256.init(new SecretKeySpec(key2.getBytes(), "HmacSHA256"));
    }

    public String callbackZalo( String jsonStr) {
        JSONObject result = new JSONObject();

        JSONObject cbdata = null;
//        cbdata = new JSONObject(jsonStr);
//        String dataStrOut = cbdata.getString("data");
//        String reqMac = cbdata.getString("mac");
        try {
            cbdata = new JSONObject(jsonStr);
            String dataStrOut = cbdata.getString("data");
            String reqMac = cbdata.getString("mac");

//            byte[] hashBytes = HmacSHA256.doFinal(dataStrOut.getBytes());
            String mac = HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key2, dataStrOut);

            // kiểm tra callback hợp lệ (đến từ ZaloPay server)
            if (!reqMac.equals(mac)) {
                result.put("return_code", -1);
                result.put("return_message", "mac not equal");
            } else {
                JSONObject data = new JSONObject(dataStrOut);
                logger.info("update order's status = success where app_trans_id = " + data.getString("app_trans_id"));

                result.put("return_code", 1);
                result.put("return_message", "success");

            }
        } catch (Exception ex) {
            result.put("return_code", 0); // ZaloPay server sẽ callback lại (tối đa 3 lần)
            result.put("return_message", ex.getMessage());
        }

        JSONObject strObj = new JSONObject(jsonStr);
        String dataStr = strObj.getString("data");

        JSONObject dataJsonObject = new JSONObject(dataStr);
        long zpId = dataJsonObject.getLong("zp_trans_id");

        String embedStr = dataJsonObject.getString("embed_data");
        JSONObject embedJsonObject = new JSONObject(embedStr);
        int paymentId = embedJsonObject.getInt("paymentId");


        System.out.println(result.get("return_code"));

        return result.toString();
    }

}
