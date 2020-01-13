package com.p.smartcitytravelers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {


    TextView txt_email,txt_messageEmail;
    Button btn_sendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        txt_email=findViewById(R.id.email);
        txt_messageEmail=findViewById(R.id.messageEmail);
        btn_sendEmail=findViewById(R.id.sendemail);
        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    void emailSendSuccessfull()
    {
        txt_messageEmail.setTextColor(getColor(R.color.colorWhite));
        txt_messageEmail.setText(getString(R.string.resetPassword_success));
    }
    void emailSendFail()
    {
        txt_messageEmail.setTextColor(getColor(R.color.colorRed));
        txt_messageEmail.setText(getString(R.string.resetPassword_fail));
    }
    void sendEmail()
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = txt_email.getText().toString();
        emailAddress=emailAddress.trim();
        auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    emailSendSuccessfull();
                }
                else
                {
                    emailSendFail();
                }
            }
        });
    }
}
