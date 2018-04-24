package com.evertecinc.libraryproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OpenATHM {

    public static void  validate(@NonNull Context context, @NonNull String BusinessToken, @NonNull Double Total, @NonNull String CartReferenceId, @Nullable Double Subtotal, @Nullable Double Tax, @Nullable List<ItemsSelected> Items)
    {
        if (context == null) {
            logForDebug("Validation","Context is NULL");
            return;
        }

        if (BusinessToken == null || BusinessToken.isEmpty()) {
            logForDebug("Validation","BusinessToken is NULL or Empty");
            return;
        }

        if (Total == null || Total < 1.00 || Total > 1500.00) {
            logForDebug("Validation","Total can't be NULL or less than 1.00");
            return;
        }

        if(CartReferenceId == null || CartReferenceId.isEmpty()) {
            logForDebug("Validation","CartReferenceId is used to return a response to your app it can't be null or empty");
            return;
        }

        PurchaseInfo purchaseInfo = new PurchaseInfo();
        purchaseInfo.setBusinessToken(BusinessToken);

        if (Subtotal != null) {
            purchaseInfo.setSubtotal(String.format(Locale.US, "%.2f", Subtotal));
        }

        if (Tax != null) {
            purchaseInfo.setTax(String.format(Locale.US, "%.2f", Tax));
        }

        if (Items != null) {
            purchaseInfo.setItemsSelectedList(Items);
        }

        purchaseInfo.setTotal(String.format(Locale.US, "%.2f", Total));
        String businessInfoJson = JsonUtil.toJason(purchaseInfo);

        if (businessInfoJson != null) {
            logForDebug("Created Json", businessInfoJson);
            execute(context, businessInfoJson, CartReferenceId);
        }else {
            logForDebug("Validation","Json creation returning NULL");
        }
    }

    private static void execute(@NonNull Context context, @NonNull String json, @NonNull String CartReferenceId){
        // ATHM APP bundle Id
        // if needed here you could add the (.debug), (.qa) and (.piloto) at the end to test
        // in the different build variants of the project.
        String ATHM_ID = "com.evertec.athmovil.android.qa";

        /* ****************************IMPORTANT*****************************************
         ***     Here are the keys that the ATHM app will be waiting for in the intent.
         ***     These keys cannot be changed or the ATHM app will not read the values sent.
         ***/    String APP_BUNDLE_ID_KEY = "bundleID";
        /**/    String JSON_DATA_KEY = "jasonData";
        /**/    String CART_REFERENCE_ID_KEY = "cartReferenceId";
        /* ****************************IMPORTANT*****************************************/

        PackageInfo athmInfo;
        int athmVersionCode = 0;

        // Looking for the ATHM app on the device
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(ATHM_ID);

        // Getting the ATHM app version code
        try {
            athmInfo = context.getPackageManager().getPackageInfo(ATHM_ID ,0);
            athmVersionCode = athmInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            logForDebug("PackageInfo", e.getMessage());
        }

        // Validating that the ATHM app was found and the versioncode is valid
        if(intent == null || athmVersionCode <= 152){
            // Opening the PlayStore because ether the app does not exist or is not the correct version.
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.evertec.athmovil.android"));
        }
        // Everything is ok launch ATHM app with the info needed.
        intent.putExtra(APP_BUNDLE_ID_KEY,context.getPackageName());
        intent.putExtra(JSON_DATA_KEY, json);
        intent.putExtra(CART_REFERENCE_ID_KEY,CartReferenceId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void logForDebug(String tag, String message){
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")){
            Log.d(tag, message);
        }
    }
}
