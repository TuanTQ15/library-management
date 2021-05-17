package com.example.qlthuvien.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlthuvien.R;

//kkkkk
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button login_button;
    EditText UserName, Password;
    String username, password;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(MainActivity.this); //Init builder.
        login_button = findViewById(R.id.bn_login);
        UserName = findViewById(R.id.edtGioiTinh);
        Password = findViewById(R.id.login_password);

        login_button.setOnClickListener(view -> {

            username = UserName.getText().toString();
            password = Password.getText().toString();
            Toast.makeText(MainActivity.this, username + password, Toast.LENGTH_SHORT).show();
            if (username.equals("") || password.equals("")) {
                builder.setTitle("Lỗi");
                //Call display Alert. Code it now.
                displayAlert("Nhập Tài Khoản và Mật Khẩu");
            } else {
                if (username.equals("admin") || password.equals("admin")) {

                    displayAlert1("Đăng Nhập Thành Công " + "Admin", "Admin");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                //new User(this).removeUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            UserName.setText("");
            Password.setText("");
        });
        AlertDialog alertDialog = builder.create(); //create
        alertDialog.show(); //Show it.
    }

    public void displayAlert1(String message, final String name) {
        builder.setMessage(message);
        builder.setPositiveButton("Tiếp Tục", (dialogInterface, i) -> {
            UserName.setText("");
            Password.setText("");
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
        AlertDialog alertDialog = builder.create(); //create
        alertDialog.show(); //Show it.
    }
}