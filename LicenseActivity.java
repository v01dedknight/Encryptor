package com.example.encryptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LicenseActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button accept = findViewById(R.id.btn_accept);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button reject = findViewById(R.id.btn_reject);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView licenseText = findViewById(R.id.license_text);

        licenseText.setText("Лицензионное соглашение на использование программы Encrypter\n" +
                "\n" +
                "    1. Общие положения\n" +
                "    Программа «Encrypter» предоставляется бесплатно по условиям данного соглашения.\n" +
                "\n" +
                "    2. Право использования\n" +
                "    Пользователь получает право бесплатного использования программы.\n" +
                "\n" +
                "    3. Ограничения\n" +
                "    Запрещено использовать программу в незаконных целях, включая взлом, распространение вредоносного ПО и другие противоправные действия.\n" +
                "\n" +
                "    4. Отказ от ответственности\n" +
                "    Программа предоставляется «как есть». Разработчик не несет ответственности за возможный ущерб от использования или невозможности использования.\n" +
                "\n" +
                "    5. Принятие соглашения\n" +
                "    Нажатием «Принять» пользователь соглашается с условиями и получает право на использование программы.");

        accept.setOnClickListener(v -> {
            Toast.makeText(this, "Лицензия принята", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        reject.setOnClickListener(v -> {
            Toast.makeText(this, "Отклонено", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
