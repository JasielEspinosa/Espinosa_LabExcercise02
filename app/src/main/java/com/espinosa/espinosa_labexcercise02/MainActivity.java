package com.espinosa.espinosa_labexcercise02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button buttonNext, buttonSP, buttonIS;
    EditText inputUsername, inputPassword;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = (EditText) findViewById(R.id.input_username);
        inputPassword = (EditText) findViewById(R.id.input_password);
        buttonNext = (Button) findViewById(R.id.btn_next);
        buttonSP = (Button) findViewById(R.id.btn_sharedPreferences);
        buttonIS = (Button) findViewById(R.id.btn_internalStorage);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);

    }

    public void callSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void triggerSharedPref(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usernameSP", inputUsername.getText().toString());
        editor.putString("passwordSP", inputPassword.getText().toString());
        editor.apply();
        Toast.makeText(this, "Successfully saved to Shared Preferences", Toast.LENGTH_LONG).show();
    }

    public void triggerInternalStorage(View view) {
        String usernameIS = inputUsername.getText().toString();
        String passwordIS = inputPassword.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(usernameIS.getBytes());
            fos.write(passwordIS.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved to Internal Storage", Toast.LENGTH_LONG).show();
    }

}
