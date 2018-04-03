package com.evertecinc.libraryproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by et56553 on 3/20/2018.
 */

public class JsonUtil {
    public static String toJason(PurchaseInfo purchaseInfo) {

        try {
            JSONObject json = new JSONObject();
            json.put("businessToken", purchaseInfo.getBusinessToken());
            json.put("subtotal", purchaseInfo.getSubtotal());
            json.put("tax", purchaseInfo.getTax());
            json.put("total", purchaseInfo.getTotal());

            JSONArray jsonArray = new JSONArray();

            for(ItemsSelected itemsSelected: purchaseInfo.getItemsSelectedList()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", itemsSelected.getName());
                jsonObject.put("description", itemsSelected.getDescription());
                jsonObject.put("price", itemsSelected.getPrice());
                jsonObject.put("quantity",itemsSelected.getQuantity());
                jsonArray.put(jsonObject);
            }

            json.put("itemsSelectedList",jsonArray);

            return json.toString();

        } catch (JSONException jsonError){
            Log.e("JSON Convert Error",jsonError.getMessage());
        }
        return null;
    }
}
