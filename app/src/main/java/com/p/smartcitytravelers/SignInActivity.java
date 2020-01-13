package com.p.smartcitytravelers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {


    TextView txt_email,txt_password,txt_error,txt_forgetPassword;
    Button sign_in,sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toast.makeText(this,"activty starts ",Toast.LENGTH_SHORT);
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null) {
            txt_email = findViewById(R.id.email);
            txt_password = findViewById(R.id.password);
            txt_error=findViewById(R.id.error);
            txt_forgetPassword=findViewById(R.id.forgetPassword);
            sign_in = findViewById(R.id.signIn);
            sign_up = findViewById(R.id.signUp);
            sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    authenticate();
                }
            });
            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signUp();
                }
            });
            txt_forgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                    startActivity(intent);
                }
            });
        }
        else
        {//user already signIn
            successfullSignIn();
        }
    }
    void signUp()
    {
        Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    void authenticate()
    {
        String email=txt_email.getText().toString();
        email=email.trim();
        String password=txt_password.getText().toString();
        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            successfullSignIn();
                        } else {
                            // If sign in fails, display a message to the user.
                            errorSignIn();
                        }

                        // ...
                    }
                });
    }
    void successfullSignIn()
    {

        Intent intent=new Intent(SignInActivity.this,HomePageActivity.class);
        startActivity(intent);
        finish();
    }
    void errorSignIn()
    {
        txt_error.setText(getString(R.string.signIn_fail));
    }

}
