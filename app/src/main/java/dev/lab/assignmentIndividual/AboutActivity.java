package dev.lab.assignmentIndividual;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView websiteTextView = findViewById(R.id.websiteTextView);
        websiteTextView.setOnClickListener(v -> {

            //Create an Intent to open the browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/FatinNabihah00/ElectricityBillCalculatorApp.git"));

            // Start the activity (open the URL)
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Handle the back button in the toolbar
        return true;
    }
}
