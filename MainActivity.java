package com.example.encryptor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private TextView textViewOutput;
    private EncryptionUtil encryptionUtil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация утилиты шифрования
        encryptionUtil = new EncryptionUtil(this);

        // Привязка UI-элементов
        editTextInput = findViewById(R.id.editTextInput);
        textViewOutput = findViewById(R.id.textViewOutput);

        Button buttonEncrypt = findViewById(R.id.buttonEncrypt);
        Button buttonDecrypt = findViewById(R.id.buttonDecrypt);
        Button buttonCopy = findViewById(R.id.buttonCopy);
        Button buttonPaste = findViewById(R.id.buttonPaste);
        Button buttonHelp = findViewById(R.id.buttonHelp);
        Button buttonDonate = findViewById(R.id.buttonDonate);
        Button buttonEditCipher = findViewById(R.id.buttonEditCipher);


        // Обработчики
        buttonEncrypt.setOnClickListener(v -> {
            String input = editTextInput.getText().toString();
            String result = encryptionUtil.encrypt(input);
            textViewOutput.setText(result);
        });

        buttonDecrypt.setOnClickListener(v -> {
            String input = editTextInput.getText().toString();
            String result = encryptionUtil.decrypt(input);
            textViewOutput.setText(result);
        });

        buttonCopy.setOnClickListener(v -> {
            String text = textViewOutput.getText().toString();
            if (!text.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("encrypted_text", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Сохранено в буфер обмена", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPaste.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.hasPrimaryClip()) {
                ClipData clipData = clipboard.getPrimaryClip();
                if (clipData != null && clipData.getItemCount() > 0) {
                    CharSequence pasteData = clipData.getItemAt(0).getText();
                    editTextInput.append(pasteData);
                }
            }
        });

        buttonHelp.setOnClickListener(v -> {
            showDialog("Программа «Encryptor» шифрует текст в бинарный формат на основе файла binary.txt.\n\nВы можете изменить этот файл вручную, чтобы задать собственные правила шифрования. При обмене одним и тем же файлом между пользователями можно безопасно передавать сообщения.\n\nДля корректной работы убедитесь, что файл binary.txt находится в папке assets.");
        });

        buttonDonate.setOnClickListener(v -> {
            String url = "https://github.com/v01dedknight";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        buttonEditCipher.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditCipherActivity.class);
            startActivity(intent);
        });
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Помощь")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
