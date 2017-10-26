package com.espinosa.espinosa_labexcercise02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Button buttonBack, buttonClear, buttonLoadSP, buttonLoadIS;
    TextView displayText;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        buttonLoadSP = (Button) findViewById(R.id.btn_loadSharedPref);
        buttonLoadIS = (Button) findViewById(R.id.btn_loadInternalStorage);

        displayText = (TextView) findViewById(R.id.displayText);

        buttonBack = (Button) findViewById(R.id.btn_back);
        buttonClear = (Button) findViewById(R.id.btn_clear);

        preferences = getSharedPreferences("sharedText.txt", MODE_PRIVATE);
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadSP(View view) {
        String usernameSP = preferences.getString("usernameSP", "NULL");
        String passwordSP = preferences.getString("passwordSP", "NULL");
        displayText.setText("Username is " + usernameSP + " and Password is " + passwordSP);
    }

    public void loadIS(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        displayText.setText(buffer.toString());
    }

    public void clearDisplay(View view){
        displayText.setText("");
    }

}
