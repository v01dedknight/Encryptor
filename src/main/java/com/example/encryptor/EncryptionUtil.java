package com.example.encryptor;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class EncryptionUtil {

    private final HashMap<String, String> encryptionMap = new HashMap<>();
    private final HashMap<String, String> decryptionMap = new HashMap<>();

    private static EncryptionUtil instance;

    // singleton для удобства доступа
    public static EncryptionUtil getInstance(Context context) {
        if (instance == null) {
            instance = new EncryptionUtil(context);
        }
        return instance;
    }

    EncryptionUtil(Context context) {
        loadEncryptionMap(context);
    }

    // Перезагрузка файла с шифрами
    public void reloadEncryptionMap(Context context) {
        encryptionMap.clear();
        decryptionMap.clear();
        loadEncryptionMap(context);
    }

    private void loadEncryptionMap(Context context) {
        InputStream is = null;
        try {
            File customFile = new File(context.getFilesDir(), "binary.txt");
            if (customFile.exists()) {
                is = new FileInputStream(customFile);
            } else {
                is = context.getAssets().open("binary.txt");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(":", 2);
                if (parts.length != 2) continue;

                String charKey = parts[0].trim();
                String binaryValue = parts[1].trim();

                // Оключение экранирования, если имеется
                if (charKey.length() > 1 && charKey.startsWith("\\")) {
                    charKey = charKey.substring(1);
                }

                // Запись в словари
                encryptionMap.put(charKey, binaryValue);
                decryptionMap.put(binaryValue, charKey);

                // Для маленьких букв добавить заглавные с тем же кодом,
                // если заглавные отсутствуют (для совместимости)
                if (charKey.length() == 1 && Character.isLowerCase(charKey.charAt(0))) {
                    String upper = Character.toString(Character.toUpperCase(charKey.charAt(0)));
                    if (!encryptionMap.containsKey(upper)) {
                        encryptionMap.put(upper, binaryValue);
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception ignored) {}
        }
    }

    public String encrypt(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch == ' ') {
                result.append("00100000 ");
                continue;
            }

            String chStr = Character.toString(ch);
            String bin = encryptionMap.get(chStr);
            if (bin != null) {
                result.append(bin).append(" ");
            } else {
                // Если символ не найден — оставить как есть (без шифра)
                result.append(chStr);
            }
        }

        return result.toString().trim();
    }

    public String decrypt(String input) {
        StringBuilder result = new StringBuilder();
        String[] parts = input.trim().split("\\s+");

        for (String part : parts) {
            if (part.equals("00100000")) {
                result.append(" ");
            } else {
                String ch = decryptionMap.get(part);
                if (ch != null) {
                    result.append(ch);
                } else {
                    // Если бинарный код не найден - пропуск
                }
            }
        }

        return result.toString();
    }
}
