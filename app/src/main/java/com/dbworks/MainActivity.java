package com.dbworks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText uname;
    public String un;
    public EditText password;
    public String pass;
    public Button login_btn;
    public TextView no_account;
    public ImageButton btn_fingerprint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_login);
        no_account = findViewById(R.id.noAccount);

        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String un = uname.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (un.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill All", Toast.LENGTH_SHORT).show();
                } else {
                    StudentDatabase studentDatabase = StudentDatabase.getStudentDatabase(getApplicationContext());
                    StudentDao studentDao = studentDatabase.studentDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            StudentEntity studentEntity = studentDao.login(un, pass);
                            if (studentEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Wrong Email n passwroed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("name",un);
                                startActivity(intent);

                            }
                        }
                    }).start();
                }
            }
        });
    }
}