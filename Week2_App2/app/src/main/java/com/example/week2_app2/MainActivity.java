package com.example.week2_app2;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ID'leri al
        EditText editText = findViewById(R.id.edtTextNum);
        Button btn = findViewById(R.id.btnEkle);
        TextView textViewElements = findViewById(R.id.txtViewElements);
        TextView textViewMax = findViewById(R.id.txtViewMax);
        TextView textViewMin = findViewById(R.id.txtViewMin);
        TextView textViewMean = findViewById(R.id.txtViewMean);
        TextView textViewOddNumbers = findViewById(R.id.txtViewOddNumbers);
        TextView textViewEvenNumbers = findViewById(R.id.txtViewEvenNumbers);


        ArrayList<Integer> numberList = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ekleme işlemi
                String number = editText.getText().toString();

                // Eğer sayıdan başka bir şey girilirse hata versin toast ile
                if (!number.isEmpty() && !number.matches("[0-9]+")) {
                    Toast.makeText(MainActivity.this, "Lütfen sadece sayı giriniz!", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;

                }


                if (!number.isEmpty()) {
                    numberList.add(Integer.parseInt(number));
                    // textViewElements.setText(String.valueOf(numberList));
                    editText.setText("");
                    // even ise evenNumbers'a ekle, odd ise oddNumbers'a ekle
                    if (Integer.parseInt(number) % 2 == 0) {
                        evenNumbers.add(Integer.parseInt(number));
                        // textViewEvenNumbers.setText(String.valueOf(evenNumbers));
                    } else {
                        oddNumbers.add(Integer.parseInt(number));
                        // textViewOddNumbers.setText(String.valueOf(oddNumbers));
                    }
                }

                // Max, Min ve Mean hesaplama
                int max = numberList.get(0);
                int min = numberList.get(0);
                int sum = 0;
                for (int i = 0; i < numberList.size(); i++) {
                    if (numberList.get(i) > max) {
                        max = numberList.get(i);
                    }
                    if (numberList.get(i) < min) {
                        min = numberList.get(i);
                    }
                    sum += numberList.get(i);
                }
                textViewMax.setText(String.valueOf(max));
                textViewMin.setText(String.valueOf(min));
                textViewMean.setText(String.format("%.2f", (double) sum / numberList.size()));

                // format numberlist and set text
                textViewElements.setText(formatNumberList(numberList.toString()));
                textViewOddNumbers.setText(formatNumberList(oddNumbers.toString()));
                textViewEvenNumbers.setText(formatNumberList(evenNumbers.toString()));




            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static String formatNumberList(String input) {
        return input.replaceAll("[\\[\\],]", "").trim().replaceAll(" +", " ");
    }
}
