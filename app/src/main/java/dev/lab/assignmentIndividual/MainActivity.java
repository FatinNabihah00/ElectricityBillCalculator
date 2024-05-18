package dev.lab.assignmentIndividual;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUnits;
    private EditText editTextRebate;
    private TextView textViewTotalCharge;
    private TextView textViewFinalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        editTextUnits = findViewById(R.id.editTextUnits);
        editTextRebate = findViewById(R.id.editTextRebate);
        textViewTotalCharge = findViewById(R.id.textViewTotalCharge);
        textViewFinalCost = findViewById(R.id.textViewFinalCost);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> calculateBill());
    }

    private void calculateBill()
    {
        Toast.makeText(this, "Bill is calculating", Toast.LENGTH_SHORT).show();

        String unitsStr = editTextUnits.getText().toString();
        String rebateStr = editTextRebate.getText().toString();

        boolean isValid = true;

        if (unitsStr.isEmpty()) {
            editTextUnits.setError("Please enter the number of units");
            isValid = false;
        }
        if (rebateStr.isEmpty()) {
            editTextRebate.setError("Please enter the rebate percentage");
            isValid = false;
        }
        if (!isValid)
        {
            return;
        }

        int units = Integer.parseInt(unitsStr);
        double rebate = Double.parseDouble(rebateStr) / 100;

        double totalCharge = 0.0;

        if (units <=200)
        {
            totalCharge = units * 0.218;
        } else if (units <= 300) {
            totalCharge = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            totalCharge = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            totalCharge = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        double finalCost = totalCharge - (totalCharge * rebate);

        textViewTotalCharge.setText(String.format("Total Charges: RM %.2f", totalCharge));
        textViewFinalCost.setText(String.format("Final Cost after Rebate: RM %.2f", finalCost));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}