package com.eng.ibrahimabdelkarim.security;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class VigenereActivity extends AppCompatActivity {
    TextInputEditText text, shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);
        text = findViewById(R.id.txtvigenere);
        shift = findViewById(R.id.txtkey);
    }

    public void Encryptvigenere(View view) {
        validateinput(false);

    }

    public void Decryptvigenere(View view) {
        validateinput(true);
    }

    private void validateinput(boolean b) {
        String txtInput, txtShift;
        txtInput = text.getText().toString();
        txtShift = shift.getText().toString();
        if (txtInput == null || txtInput.equals("") || txtInput.isEmpty()) {
            text.setError("Text is Empty");
            return;
        } else if (txtShift == null || txtShift.equals("") || txtShift.isEmpty()) {
            shift.setError("key: is Empty");
            return;
        } else {
            if (b) {
                //decrept
                char[] key = filterKey(txtShift);
                for (int i = 0; i < txtShift.length(); i++) {
                    key[i] = (char) ((26 - key[i]) % 26);
                }
                crypt(txtInput,key);

            } else {
                //incrypt
                crypt(txtInput,filterKey(txtShift));
            }
        }
    }

    void crypt(String input, char[] key) {
        String output = "";
        for (int i = 0, j = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isUppercase(c)) {
                output += Character.toString((char) ((c - 65 + key[j % key.length]) % 26 + 65));
                j++;
            } else if (isLowercase(c)) {
                output += Character.toString((char) ((c - 97 + key[j % key.length]) % 26 + 97));
                j++;
            } else {
                output += input.charAt(i);
            }
        }
        text.setText(output);
    }

    char[] filterKey(String key) {
        char[] result = new char[key.length()];
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (isLetter(c))
                result[i] = (char) ((c - 65) % 32);
        }
        return result;
    }

    boolean isLetter(char c) {
        return isUppercase(c) || isLowercase(c);
    }

    boolean isUppercase(char c) {
        return 65 <= c && c <= 90;
    }

    boolean isLowercase(char c) {
        return 97 <= c && c <= 122;
    }

}
