package com.Sberbank.Sberbank.payments;

import java.util.HashMap;
import java.util.Map;

public class QiwiPayer {
    public QiwiPayer() {

    }

    public Map<String, String> createPaymentForm(String sum_int, String sum_fraction, String payee, String payer, String buy_id) {
        Map<String, String> form_params = new HashMap<String, String>();
        form_params.put("sum_int", sum_int);
        form_params.put("sum_fraction", sum_fraction);
        form_params.put("payee", payee);
        form_params.put("description", payer + " " + buy_id);

        return form_params;
    }

    public boolean checkPayment(String payer, String buy_id) {
        return true;
    }
}
