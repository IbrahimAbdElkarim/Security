package com.eng.ibrahimabdelkarim.security;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RailfenceActivity extends AppCompatActivity {
    TextInputEditText text, shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_railfence);
        text = findViewById(R.id.txtrail);
        shift = findViewById(R.id.shiftrail);
    }

    public void EncryptRail(View view) {
        validateinput(false);
    }

    public void DecryptRail(View view) {
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
            shift.setError("row no: is Empty");
            return;
        } else {
            if (b) {
                //decrept
                Decryption(txtInput, Integer.parseInt(txtShift));
            } else {
                //incrypt
                Encryption(txtInput, Integer.parseInt(txtShift));
            }
        }
    }

    void Encryption(String plainText, int depth) {
        int r = depth, len = plainText.length();
        int c = len / depth;
        if (len % depth != 0) {
            c = len / depth + 1;
        }
        char mat[][] = new char[r][c];
        int k = 0;
        String cipherText = "";
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != len) {
                    mat[j][i] = plainText.charAt(k++);
                } else {
                    mat[j][i] = 'X';
                }
            }
        }

        //build cipher text from matrix
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cipherText += mat[i][j];
            }
        }
        text.setText(cipherText.toUpperCase());
    }

    void Decryption(String cipherText, int depth) {
        int r = depth, len = cipherText.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;

        String plainText = "";


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cipherText.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                plainText += mat[j][i];
            }
        }

        text.setText(plainText);
    }
}


