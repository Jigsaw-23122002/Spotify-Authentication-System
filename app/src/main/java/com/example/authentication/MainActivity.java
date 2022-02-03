package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authentication.database.Database;
import com.example.authentication.model.Model;

public class MainActivity extends AppCompatActivity {

    Database database = new Database(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button SignUpButton = (Button) findViewById(R.id.SignUpButton);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Username = findViewById(R.id.Username);
                EditText Email = findViewById(R.id.Email);
                EditText Password = findViewById(R.id.Password);
                EditText PasswordConfirmation = findViewById(R.id.PasswordConfirmation);

                String username = String.valueOf(Username.getText());
                String email = String.valueOf(Email.getText());
                String password = String.valueOf(Password.getText());
                String passwordConfirmation = String.valueOf(PasswordConfirmation.getText());

                if ((!password.equals(passwordConfirmation))) {
                    TextView SetStatus = (TextView)findViewById(R.id.SetStatus);
                    SetStatus.setGravity(Gravity.CENTER);
                    SetStatus.setText("Confirmation doesn't match with the password");
                } else {
                    Model model = new Model(username,email,password);
                    Boolean status = database.isPresent(model);
                    if(status){
                        TextView SetStatus = (TextView)findViewById(R.id.SetStatus);
                        SetStatus.setText("The user already existed");
                        SetStatus.setGravity(Gravity.CENTER);
                        Log.d("Details","The user already present");
                    }
                    else {
                        TextView SetStatus = (TextView)findViewById(R.id.SetStatus);
                        SetStatus.setText("User Registered Successfully");
                        SetStatus.setGravity(Gravity.CENTER);
                        Log.d("Details","Adding the data to database");
                        database.addData(model);
                    }
                }
            }
        });

        Button LoginPageRedirect = (Button) findViewById(R.id.LoginPageRedirect);
        LoginPageRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        database.peek();
    }
}