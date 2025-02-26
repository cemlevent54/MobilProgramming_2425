package com.example.week2_app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

        EditText editText1 = findViewById(R.id.editTextText1);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Merhaba "+ editText1.getText().toString() , Toast.LENGTH_SHORT).show();
                editText1.setText("");
            }
        });

        EditText word1 = findViewById(R.id.edtText1);
        EditText word2 = findViewById(R.id.edtText2);
        TextView txtresult = findViewById(R.id.textViewResult);
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = word1.getText().toString();
                String str2 = word2.getText().toString();
                txtresult.setText(str1 + " " + str2);
                word1.setText("");
                word2.setText("");
            }
        });

        // 5 elemanlı yemek arrayi tanımla
        String[] yemekler = {"Kebap", "Pizza", "Hamburger", "Tavuk", "Balık"};
        CheckBox chcbox1 = findViewById(R.id.checkBox);
        CheckBox chcbox2 = findViewById(R.id.checkBox2);
        CheckBox chcbox3 = findViewById(R.id.checkBox3);
        CheckBox chcbox4 = findViewById(R.id.checkBox4);
        CheckBox chcbox5 = findViewById(R.id.checkBox5);

        // elemanlarını checkboxlara ata
        chcbox1.setText(yemekler[0]);
        chcbox2.setText(yemekler[1]);
        chcbox3.setText(yemekler[2]);
        chcbox4.setText(yemekler[3]);
        chcbox5.setText(yemekler[4]);
        // textview
        TextView txtsecilenler = findViewById(R.id.textViewFood);

        // seçilen checkboxları bul
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secilenler = "";
                // checkboxlar secili mi for dongusu ile kontrol et
                if(chcbox1.isChecked()){
                    secilenler += yemekler[0] + " ";
                }
                if(chcbox2.isChecked()){
                    secilenler += yemekler[1] + " ";
                }
                if(chcbox3.isChecked()){
                    secilenler += yemekler[2] + " ";
                }
                if(chcbox4.isChecked()){
                    secilenler += yemekler[3] + " ";
                }
                if(chcbox5.isChecked()){
                    secilenler += yemekler[4] + " ";
                }
                // secilenleri textviewde göster
                txtsecilenler.setText(secilenler);

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}