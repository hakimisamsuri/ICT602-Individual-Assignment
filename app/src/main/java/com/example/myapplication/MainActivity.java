package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCalculate, btnClear;

    EditText etNum1, etNum2;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        textView = findViewById(R.id.textView);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            Toast.makeText(this, "About Page", Toast.LENGTH_SHORT).show();
            return true;

        } else if (selected == R.id.menuGuide) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Guide Page", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        String number1 = etNum1.getText().toString();
        String number2 = etNum2.getText().toString();

        double num1, num2;
        double total = 0;
        double finalCost;

        num1 = Double.parseDouble(number1);
        num2 = Double.parseDouble(number2);

        if (num1 <= 200) {
            total = num1 * 0.218;
        } else if (num1 <= 300) {
            total = (200 * 0.218) + ((num1 - 200) * 0.334);
        } else if (num1 <= 600) {
            total = (200 * 0.218) + (100 * 0.334) + ((num1 - 300) * 0.516);
        } else if (num1 <= 900) {
            total = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((num1 - 600) * 0.546);
        }

        finalCost = total - (total * (num2 / 100));

        // Format the final cost to two decimal places
        String formattedCost = String.format("%.2f", finalCost);

        textView.setText("TOTAL: RM " + formattedCost);

        // Clear the text in both EditText fields
        if (view.getId() == R.id.btnCalculate) {
            textView.setText("TOTAL: RM " + formattedCost);
        } else if (view.getId() == R.id.btnClear) {
            etNum1.setText("");
            etNum2.setText("");
            textView.setText("TOTAL: RM 0.00");
        }
    }
}