package com.p.smartcitytravelers;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    TextView txt_name,txt_email,txt_password,txt_comfirmPassword,txt_error;
    Button btn_register;
    String errorMessage="•  ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_name=findViewById(R.id.name);
        txt_email=findViewById(R.id.email);
        txt_password=findViewById(R.id.password);
        txt_comfirmPassword=findViewById(R.id.confirmPassword);
        txt_error=findViewById(R.id.error);
        btn_register=findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    boolean isNameValid(String name)
    {
        if(name.trim().equals(""))
        {
            errorMessage+="Name can't be empty\n";
            return false;
        }
        return true;
    }
    boolean isEmailValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()==true)
        {
            return true;
        }
        else
        {
            errorMessage+="Invalid Email Adress";
            return false;
        }
    }
    boolean isPasswordValid(String password,String confirmPassword)
    {
        if(password.length()<8)
        {
            errorMessage+="Password should be atleat 8 character long";
            return false;
        }
        else
        {
            if(!password.equals(confirmPassword))
            {
                errorMessage+="Password and Confirm Password are not same";
                return false;
            }
        }

        return true;
    }
    void signUp()
    {
        final String name=txt_name.getText().toString();
        String email=txt_email.getText().toString();
        email=email.trim();
        String password=txt_password.getText().toString();
        String confirmpassword=txt_comfirmPassword.getText().toString();
        if(isNameValid(name)&&isEmailValid(email) && isPasswordValid(password,confirmpassword)) {
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                logoutUser();
                                                Toast.makeText(SignUpActivity.this, "Account Created Successfully Please Sign In", Toast.LENGTH_LONG).show();
                                                goToSignIn();

                                            }
                                        });
                            } else {
                                errorSignUp();
                            }

                            // ...
                        }
                    });
        }
        else
        {
            displayMessage();
        }

    }

    void logoutUser()
    {
        FirebaseAuth.getInstance().signOut();
    }
    void displayMessage()
    {
        txt_error.setText(errorMessage);
        errorMessage="•  ";
    }
    void goToSignIn()
    {
        Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(intent);
        finish();
    }
    void errorSignUp()
    {
        txt_error.setText(getString(R.string.signUp_fail));
    }

}
