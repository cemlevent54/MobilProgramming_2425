package com.example.week1_application2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView lblSonuc = findViewById(R.id.lblSonuc);
        EditText txtBox1 = findViewById(R.id.txtBoxFirst);
        EditText txtBox2 = findViewById(R.id.txtBoxSecond);
        Button btnplus = findViewById(R.id.btnPlus);
        Button btnminus = findViewById(R.id.btnMinus);
        Button btnmult = findViewById(R.id.btnMultiplication);
        Button btndiv = findViewById(R.id.btnDivision);

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = txtBox1.getText().toString().trim();
                String input2 = txtBox2.getText().toString().trim();

                if (TextUtils.isEmpty(input1)) {
                    txtBox1.setError("Lütfen sayı giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(input2)) {
                    txtBox2.setError("Lütfen sayı giriniz!");
                    return;
                }

                try {
                    Float first_number = Float.parseFloat(input1);
                    Float second_number = Float.parseFloat(input2);
                    Float result = first_number + second_number;
                    String res = first_number.toString() + " + " + second_number.toString() + " = " + result.toString();
                    lblSonuc.setText(res);
                    txtBox1.setText("");
                    txtBox2.setText("");


                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Hata: Geçersiz sayı girişi!", Toast.LENGTH_SHORT).show();
                    if (!input1.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox1.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                    if (!input2.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox2.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                }
            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = txtBox1.getText().toString().trim();
                String input2 = txtBox2.getText().toString().trim();

                if (TextUtils.isEmpty(input1)) {
                    txtBox1.setError("Lütfen sayı giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(input2)) {
                    txtBox2.setError("Lütfen sayı giriniz!");
                    return;
                }

                try {
                    Float first_number = Float.parseFloat(input1);
                    Float second_number = Float.parseFloat(input2);
                    Float result = first_number - second_number;
                    String res = first_number.toString() + " - " + second_number.toString() + " = " + result.toString();
                    lblSonuc.setText(res);
                    txtBox1.setText("");
                    txtBox2.setText("");


                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Hata: Geçersiz sayı girişi!", Toast.LENGTH_SHORT).show();
                    if (!input1.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox1.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                    if (!input2.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox2.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                }
            }
        });

        btnmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = txtBox1.getText().toString().trim();
                String input2 = txtBox2.getText().toString().trim();

                if (TextUtils.isEmpty(input1)) {
                    txtBox1.setError("Lütfen sayı giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(input2)) {
                    txtBox2.setError("Lütfen sayı giriniz!");
                    return;
                }

                try {
                    Float first_number = Float.parseFloat(input1);
                    Float second_number = Float.parseFloat(input2);
                    Float result = first_number * second_number;
                    String res = first_number.toString() + " * " + second_number.toString() + " = " + result.toString();
                    lblSonuc.setText(res);
                    txtBox1.setText("");
                    txtBox2.setText("");


                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Hata: Geçersiz sayı girişi!", Toast.LENGTH_SHORT).show();
                    if (!input1.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox1.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                    if (!input2.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox2.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                }
            }
        });



        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = txtBox1.getText().toString().trim();
                String input2 = txtBox2.getText().toString().trim();

                // Boş giriş kontrolü
                if (TextUtils.isEmpty(input1)) {
                    txtBox1.setError("Lütfen sayı giriniz!");
                    return;
                }
                if (TextUtils.isEmpty(input2)) {
                    txtBox2.setError("Lütfen sayı giriniz!");
                    return;
                }

                try {
                    // Sayıya çevirme işlemi ve hata kontrolü
                    Float first_number = Float.parseFloat(input1);
                    Float second_number = Float.parseFloat(input2);

                    // 0'a bölme kontrolü
                    if (second_number == 0) {
                        txtBox2.setError("Sıfıra bölme hatası! 0 dışında bir sayı giriniz.");
                        Toast.makeText(MainActivity.this, "Hata: Bir sayı sıfıra bölünemez!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // İlk sayı ikinci sayıdan küçükse hata verdir
                    if (first_number < second_number) {
                        Toast.makeText(MainActivity.this, "Hata: İlk sayı, ikinci sayıdan büyük olmalıdır!", Toast.LENGTH_SHORT).show();
                        txtBox1.setError("İlk sayı ikinci sayıdan büyük olmalıdır!");
                        return;
                    }

                    // Bölme işlemi
                    Float result = first_number / second_number;
                    String res = first_number.toString() + " / " + second_number.toString() + " = " + result.toString();
                    lblSonuc.setText(res);
                    txtBox1.setText("");
                    txtBox2.setText("");

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Hata: Geçersiz sayı girişi!", Toast.LENGTH_SHORT).show();
                    if (!input1.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox1.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                    if (!input2.matches("-?\\d+(\\.\\d+)?")) {
                        txtBox2.setError("Geçersiz giriş! Lütfen bir sayı giriniz.");
                    }
                }
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}