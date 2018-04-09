package com.bleedbytes.riguserdata;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener
{

    private EditText etEmailup,etPassup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmailup =(EditText)findViewById(R.id.etEmailup);
        etPassup =(EditText)findViewById(R.id.etPassup);

        mAuth = FirebaseAuth.getInstance();
    }



    private void regiteruser()
    {
        String remail = etEmailup.getText().toString().trim();
        String rpassword = etPassup.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher((CharSequence) etEmailup).matches())
        {
            etEmailup.setError("enter valid email");
            etEmailup.requestFocus();
            return;
        }
        if(remail.isEmpty()){
            etEmailup.setError("mail-Id required");
            etEmailup.requestFocus();
            return;
        }

        if(rpassword.isEmpty())
        {
            etPassup.setError("etPassup required");
            etPassup.requestFocus();
            return;
        }

        if(etPassup.length()<6)
        {
            etPassup.setError("Minimum length is 6");
            etPassup.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(remail,rpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btSignup:
                regiteruser();
                break;

            case R.id.tvSignin:
                startActivity(new Intent(this,MainActivity.class));
        }
    }
}
