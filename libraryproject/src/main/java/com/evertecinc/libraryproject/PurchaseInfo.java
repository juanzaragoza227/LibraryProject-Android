package com.evertecinc.libraryproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by et56553 on 3/19/2018.
 */

public class PurchaseInfo {

    private String businessToken;
    private String subtotal;
    private String tax;
    private String total;
    private List<ItemsSelected> itemsSelectedList =  new ArrayList<>();


    public String getBusinessToken() {return businessToken;}

    public void setBusinessToken(String businessToken) {this.businessToken = businessToken;}

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ItemsSelected> getItemsSelectedList() {
        return itemsSelectedList;
    }

    public void setItemsSelectedList(List<ItemsSelected> itemsSelectedList) {this.itemsSelectedList = itemsSelectedList;}

}