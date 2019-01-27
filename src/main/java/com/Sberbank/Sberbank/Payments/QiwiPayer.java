package com.Sberbank.Sberbank.Payments;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QiwiPayer {
    public QiwiPayer() {

    }

    public Map<String, String> createPaymentForm(String sum_int, String sum_fraction, String payee, String payer, String buy_id) {
        Map<String, String> form_params = new HashMap<String, String>();
        form_params.put("sum_int", sum_int);
        form_params.put("sum_fraction", sum_fraction);
        form_params.put("payee", payee);
        form_params.put("description", buy_id);

        return form_params;
    }

    public static boolean checkPayment(Integer sum_int, String sum_fraction, String payee, Long payer, Long buy_id, String token) {

        try {
            String url = "https://edge.qiwi.com/payment-history/v2/persons/" + payee + "/payments?rows=1";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            return checkWithRegExp(response.toString(), "^.*\"status\":\"SUCCESS\".*$") &&
                    checkWithRegExp(response.toString(), "^.*\"sum\":.\"amount\":" + sum_int + ",\"currency\":643..*$") &&
                    checkWithRegExp(response.toString(), "^.*\"personId\":" + payer + ".*$") &&
                    checkWithRegExp(response.toString(), "^.*\"comment\":\"" + buy_id + "\".*$");
//            System.out.println(checkWithRegExp(response.toString(), "^.*\"status\":\"SUCCESS\".*$"));
//            System.out.println(checkWithRegExp(response.toString(), "^.*\"sum\":.\"amount\":" + sum_int + ",\"currency\":643..*$"));
//            System.out.println(checkWithRegExp(response.toString(), "^.*\"personId\":" + payer + ".*$"));
//            System.out.println(checkWithRegExp(response.toString(), "^.*\"comment\":\"" + buy_id + "\".*$"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("null :(");
            return false;
        }
    }

    public static boolean checkWithRegExp(String userNameString, String template) {
        Pattern p = Pattern.compile(template);
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}


