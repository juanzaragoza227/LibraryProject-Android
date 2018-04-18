package com.evertecinc.examplelibraryproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.evertecinc.libraryproject.OpenATHM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_example);
    }

    //Use the class of your layout to implement this method
    public void OnClickPayWithATHM(View view) {
        OpenATHM.validate(this,"4444",null,null,1.00,null);
    }
}