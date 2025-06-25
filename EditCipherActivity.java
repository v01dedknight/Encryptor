package com.example.encryptor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class EditCipherActivity extends Activity {

    private EditText editTextCipher;

    private static final String RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:',.<>/?\\\"";

    private static final String DEFAULT_BINARY_CIPHER =
            "a: 01100001\n" +
                    "b: 01100010\n" +
                    "c: 01100011\n" +
                    "d: 01100100\n" +
                    "e: 01100101\n" +
                    "f: 01100110\n" +
                    "g: 01100111\n" +
                    "h: 01101000\n" +
                    "i: 01101001\n" +
                    "j: 01101010\n" +
                    "k: 01101011\n" +
                    "l: 01101100\n" +
                    "m: 01101101\n" +
                    "n: 01101110\n" +
                    "o: 01101111\n" +
                    "p: 01110000\n" +
                    "q: 01110001\n" +
                    "r: 01110010\n" +
                    "s: 01110011\n" +
                    "t: 01110100\n" +
                    "u: 01110101\n" +
                    "v: 01110110\n" +
                    "w: 01110111\n" +
                    "x: 01111000\n" +
                    "y: 01111001\n" +
                    "z: 01111010\n" +

                    "A: 01000001\n" +
                    "B: 01000010\n" +
                    "C: 01000011\n" +
                    "D: 01000100\n" +
                    "E: 01000101\n" +
                    "F: 01000110\n" +
                    "G: 01000111\n" +
                    "H: 01001000\n" +
                    "I: 01001001\n" +
                    "J: 01001010\n" +
                    "K: 01001011\n" +
                    "L: 01001100\n" +
                    "M: 01001101\n" +
                    "N: 01001110\n" +
                    "O: 01001111\n" +
                    "P: 01010000\n" +
                    "Q: 01010001\n" +
                    "R: 01010010\n" +
                    "S: 01010011\n" +
                    "T: 01010100\n" +
                    "U: 01010101\n" +
                    "V: 01010110\n" +
                    "W: 01010111\n" +
                    "X: 01011000\n" +
                    "Y: 01011001\n" +
                    "Z: 01011010\n" +

                    "а: 1101000010110000\n" +
                    "б: 1101000010110001\n" +
                    "в: 1101000010110010\n" +
                    "г: 1101000010110011\n" +
                    "д: 1101000010110100\n" +
                    "е: 1101000010110101\n" +
                    "ё: 1101000110010001\n" +
                    "ж: 1101000010110110\n" +
                    "з: 1101000010110111\n" +
                    "и: 1101000010111000\n" +
                    "й: 1101000010111001\n" +
                    "к: 1101000010111010\n" +
                    "л: 1101000010111011\n" +
                    "м: 1101000010111100\n" +
                    "н: 1101000010111101\n" +
                    "о: 1101000010111110\n" +
                    "п: 1101000010111111\n" +
                    "р: 1101000110000000\n" +
                    "с: 1101000110000001\n" +
                    "т: 1101000110000010\n" +
                    "у: 1101000110000011\n" +
                    "ф: 1101000110000100\n" +
                    "х: 1101000110000101\n" +
                    "ц: 1101000110000110\n" +
                    "ч: 1101000110000111\n" +
                    "ш: 1101000110001000\n" +
                    "щ: 1101000110001001\n" +
                    "ъ: 1101000110001010\n" +
                    "ы: 1101000110001011\n" +
                    "ь: 1101000110001100\n" +
                    "э: 1101000110001101\n" +
                    "ю: 1101000110001110\n" +
                    "я: 1101000110001111\n" +

                    "А: 1101000010010000\n" +
                    "Б: 1101000010010001\n" +
                    "В: 1101000010010010\n" +
                    "Г: 1101000010010011\n" +
                    "Д: 1101000010010100\n" +
                    "Е: 1101000010010101\n" +
                    "Ё: 1101000010000001\n" +
                    "Ж: 1101000010010110\n" +
                    "З: 1101000010010111\n" +
                    "И: 1101000010011000\n" +
                    "Й: 1101000010011001\n" +
                    "К: 1101000010011010\n" +
                    "Л: 1101000010011011\n" +
                    "М: 1101000010011100\n" +
                    "Н: 1101000010011101\n" +
                    "О: 1101000010011110\n" +
                    "П: 1101000010011111\n" +
                    "Р: 1101000010100000\n" +
                    "С: 1101000010100001\n" +
                    "Т: 1101000010100010\n" +
                    "У: 1101000010100011\n" +
                    "Ф: 1101000010100100\n" +
                    "Х: 1101000010100101\n" +
                    "Ц: 1101000010100110\n" +
                    "Ч: 1101000010100111\n" +
                    "Ш: 1101000010101000\n" +
                    "Щ: 1101000010101001\n" +
                    "Ъ: 1101000010101010\n" +
                    "Ы: 1101000010101011\n" +
                    "Ь: 1101000010101100\n" +
                    "Э: 1101000010101101\n" +
                    "Ю: 1101000010101110\n" +
                    "Я: 1101000010101111\n" +

                    ".: 00101110\n" +
                    ",: 00101100\n" +
                    "!: 00100001\n" +
                    "?: 00111111\n" +
                    ";: 00111011\n" +
                    "-: 00101101\n" +
                    "': 00100111\n" +
                    "\": 00100010\n" +
                    "(: 00101000\n" +
                    "): 00101001\n" +
                    "[: 01011011\n" +
                    "]: 01011101\n" +
                    "{: 01111011\n" +
                    "}: 01111101\n" +
                    "/: 00101111\n" +
                    "\\: 01011100\n" +
                    "|: 01111100\n" +
                    "@: 01000000\n" +
                    "#: 00100011\n" +
                    "$: 00100100\n" +
                    "%: 00100101\n" +
                    "^: 01011110\n" +
                    "&: 00100110\n" +
                    "*: 00101010\n" +
                    "+: 00101011\n" +
                    "=: 00111101\n" +
                    "~: 01111110\n" +

                    "0: 00110000\n" +
                    "1: 00110001\n" +
                    "2: 00110010\n" +
                    "3: 00110011\n" +
                    "4: 00110100\n" +
                    "5: 00110101\n" +
                    "6: 00110110\n" +
                    "7: 00110111\n" +
                    "8: 00111000\n" +
                    "9: 00111001\n";

    private EncryptionUtil encryptionUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cipher);

        editTextCipher = findViewById(R.id.editTextCipher);
        Button buttonSave = findViewById(R.id.buttonSaveCipher);
        Button buttonGenerate = findViewById(R.id.buttonGenerateCipher);
        Button buttonDefaultBinary = findViewById(R.id.buttonDefaultBinaryCipher);

        loadCipher();

        // Получить объект EncryptionUtil
        encryptionUtil = EncryptionUtil.getInstance(this);

        buttonSave.setOnClickListener(v -> {
            String content = editTextCipher.getText().toString();
            if (saveCipher(content)) {
                Toast.makeText(this, "Шифр сохранён. Перезагрузите приложение для применения изменений.", Toast.LENGTH_LONG).show();

                // Обновить словарь шифра после сохранения
                if (encryptionUtil != null) {
                    encryptionUtil.reloadEncryptionMap(this);
                }

                finish();
            } else {
                Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGenerate.setOnClickListener(v -> {
            String currentText = editTextCipher.getText().toString();
            String generated = generateRandomCipher(currentText);
            editTextCipher.setText(generated);
        });

        buttonDefaultBinary.setOnClickListener(v -> {
            editTextCipher.setText(DEFAULT_BINARY_CIPHER);
        });
    }

    private void loadCipher() {
        File file = new File(getFilesDir(), "binary.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();
                editTextCipher.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("binary.txt"), StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();
                editTextCipher.setText(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean saveCipher(String content) {
        try (FileOutputStream fos = openFileOutput("binary.txt", Context.MODE_PRIVATE)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Генерировать случайную строку из RANDOM_CHARS длиной length
    private String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(RANDOM_CHARS.length());
            sb.append(RANDOM_CHARS.charAt(index));
        }
        return sb.toString();
    }

    // Генерировать новый шифр на основе текущего текста
    private String generateRandomCipher(String currentCipherText) {
        StringBuilder sb = new StringBuilder();

        String[] lines = currentCipherText.split("\n");
        for (String line : lines) {
            if (!line.contains(":")) {
                sb.append(line).append("\n");
                continue;
            }
            String[] parts = line.split(":", 2);
            if (parts.length < 2) {
                sb.append(line).append("\n");
                continue;
            }
            String symbol = parts[0].trim();
            String code = parts[1].trim();

            String newCode = generateRandomCode(code.length());

            sb.append(symbol).append(":").append(newCode).append("\n");
        }
        return sb.toString();
    }
}
