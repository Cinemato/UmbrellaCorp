package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity
{
    Button submitBtn;
    EditText nameTxt, emailTxt, phoneTxt, messageTxt;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        submitBtn = (Button) findViewById(R.id.submitButton);
        nameTxt = (EditText) findViewById(R.id.nameEditText);
        emailTxt = (EditText) findViewById(R.id.emailEditText);
        phoneTxt = (EditText) findViewById(R.id.phoneEditText);
        messageTxt = (EditText) findViewById(R.id.messageEditText);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Messages");

                String name = nameTxt.getEditableText().toString();
                String email = emailTxt.getEditableText().toString();
                String phoneNo = phoneTxt.getEditableText().toString();
                String message = messageTxt.getEditableText().toString();

                Message m = new Message(name, email, phoneNo, message);

                if(!name.isEmpty() && !email.isEmpty() && !phoneNo.isEmpty() && !message.isEmpty())
                {
                    reference.child(m.getMessageId()).setValue(m);
                    Toast.makeText(getApplicationContext(), "Thanks For Your Message!\nMessage ID: " + m.getMessageId(), Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "ERROR:Not All Inputs Are Filled", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
