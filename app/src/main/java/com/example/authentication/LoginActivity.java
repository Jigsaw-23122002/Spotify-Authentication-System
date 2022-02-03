package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.authentication.database.Database;
import com.example.authentication.model.Model;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    public static final String User_Key="Username";

    Database database = new Database(LoginActivity.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Username = findViewById(R.id.Username_);
                EditText Email = findViewById(R.id.Email_);
                EditText Password = findViewById(R.id.Password_);

                String username = Username.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                Model model = new Model(username,email,password);
                Boolean status = database.isCorrect(model);

                if(status){
                    Log.d("Details","Logged In Successfully");
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.putExtra(User_Key,username);
                    startActivity(intent);
                }
                else{
                    TextView LoginSetStatus = (TextView)findViewById(R.id.LoginSetStatus);
                    LoginSetStatus.setText("Please enter the valid Details");
                    Log.d("Details","Invalid Login Id/Password");
                }
            }
        });
    }
}
