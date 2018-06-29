package com.eng.ibrahimabdelkarim.security;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class CaesarActivity extends AppCompatActivity {
    TextInputEditText text, shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);
        text = findViewById(R.id.txt);
        shift = findViewById(R.id.shift);
    }

    public void Encrypt(View view) {
        validateinput(false);
    }


    public void Decrypt(View view) {
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
            shift.setError("Shift no: is Empty");
            return;
        } else {
            if (b) {
                //decrept
                Integer shift = Integer.valueOf(txtShift);
                if (shift < 0 || shift >= 26) {
                    this.shift.setError("Shift is out of range");
                    return;
                }
                shift = (26 - shift) % 26;
                caesarShift(txtInput, shift);
            } else {
                //incrypt
                caesarShift(txtInput, Integer.valueOf(txtShift));
            }
        }
    }

    private void caesarShift(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (65 <= c && c <= 90)
                result += Character.toString((char) ((c - 65 + shift) % 26 + 65));  // Uppercase
            else if (97 <= c && c <= 122)
                result += Character.toString((char) ((c - 97 + shift) % 26 + 97));  // Lowercase
            else result += Character.toString((char) (i));  // Copy
        }
        this.text.setText(result);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.caesar:
                startActivity(new Intent(getApplicationContext(), CaesarActivity.class));
                return true;
            case R.id.vigenere:
                startActivity(new Intent(getApplicationContext(), VigenereActivity.class));
                return true;
            case R.id.rail:
                startActivity(new Intent(getApplicationContext(), RailfenceActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
