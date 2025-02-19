package com.example.week1_application1;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView lblname = findViewById(R.id.lblName);
        TextView lblsurname = findViewById(R.id.lblSurname);
        EditText edttext = findViewById(R.id.txtBoxName);
        EditText edttexttoast = findViewById(R.id.txtBoxNameToast);
        TextView lblresult = findViewById(R.id.lblResult);
        Button btnshowlabel = findViewById(R.id.btnShowLabel);
        Button btnshowtoast = findViewById(R.id.btnShowToast);
        Button btnClick = findViewById(R.id.btnTikla);


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lblname ve lblsurname değiştir
                String temp = lblname.getText().toString();
                lblname.setText(lblsurname.getText().toString());
                lblsurname.setText(temp);
            }
        });

        btnshowlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = edttext.getText().toString().trim();

                if (inputText.isEmpty()) {
                    edttext.setError("Lütfen bir metin girin!");
                    return;
                }

                String txtBoxResult = inputText + " Hello Word!";
                lblresult.setText(txtBoxResult);
                edttext.setText("");
            }
        });

        btnshowtoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = edttexttoast.getText().toString().trim();

                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Hata: Boş veri girildi!", Toast.LENGTH_SHORT).show();
                    edttexttoast.setError("Lütfen bir şey yazın!");
                    return;
                }

                String message = inputText + " Nasılsın?";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                edttexttoast.setText("");
            }
        });








        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}