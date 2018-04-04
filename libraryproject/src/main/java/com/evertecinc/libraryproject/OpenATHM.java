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

import java.util.List;
import java.util.Locale;

public class OpenATHM {

    public static void  execute(@NonNull Context context, @NonNull String BusinessToken, @Nullable Double Subtotal, @Nullable Double Tax,
                                @NonNull Double Total, @Nullable List<ItemsSelected> Items)
    {
        PurchaseInfo purchaseInfo = new PurchaseInfo();
        purchaseInfo.setBusinessToken(BusinessToken);
        if(Subtotal != null) {
            purchaseInfo.setSubtotal(String.format(Locale.US, "%.2f", Subtotal));
        }
        if(Tax != null) {
            purchaseInfo.setTax(String.format(Locale.US, "%.2f", Tax));
        }
        purchaseInfo.setTotal(String.format(Locale.US,"%.2f",Total));
        purchaseInfo.setItemsSelectedList(Items);
        String businessInfoJson =  JsonUtil.toJason(purchaseInfo);
        Log.d("Created JSON", businessInfoJson);
        if(businessInfoJson != null) {
            openAthmApp(context, businessInfoJson);
        }else {
            Toast.makeText(context,"Error creating json returned value equals null", Toast.LENGTH_LONG).show();
        }
    }

    private static void openAthmApp(@NonNull Context context, @NonNull String json){
        // ATHM APP bundle Id
        // if needed here you could add the (.debug), (.qa) and (.piloto) at the end to test
        // in the different build variants of the project.
        String ATHM_ID = "com.evertec.athmovil.android.debug";

        /* ****************************IMPORTANT*****************************************
         ***     Here are the keys that the ATHM app will be waiting for in the intent.
         ***     These keys cannot be changed or the ATHM app will not read the values sent.
         ***/    String APP_BUNDLE_ID_KEY = "bundleID";
        /**/     String JSON_DATA_KEY = "jasonData";
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
            e.printStackTrace();
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
