package com.example.book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerActivity extends AppCompatActivity {
    Button btnContinue, btnScan;
    public String Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scanner);

        btnScan = findViewById(R.id.btnScan);
        btnContinue = findViewById(R.id.btnContinue);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Initialize intent integrator

                IntentIntegrator intentIntegrator = new IntentIntegrator(ScannerActivity.this);
//                Set Prompt text
                intentIntegrator.setPrompt("For flash, use volume up button");
//                set beep
                intentIntegrator.setBeepEnabled(true);
//                Locked Orientation
                intentIntegrator.setOrientationLocked(true);
//                set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
//                Initiate scan
                intentIntegrator.initiateScan();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Result = intentResult.getContents();

//        Check condition
        if (intentResult.getContents() != null) {

//            where result content is not null
//            Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(ScannerActivity.this);

//            Set Title
            builder.setTitle("Result");

//            set message
            builder.setMessage(intentResult.getContents());

//            set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    Dismiss Dialog
                    dialogInterface.dismiss();
                }
            });
//            show alert dialog
            builder.show();
        } else {
//            When result content is null
//            Display Toast
            Toast.makeText(this, "OOPS....You did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }
}
