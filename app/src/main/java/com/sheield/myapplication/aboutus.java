package com.sheield.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class aboutus extends AppCompatActivity {
    String phone="9811660886";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        final FloatingActionButton mFab=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        FloatingActionButton mcall=(FloatingActionButton)findViewById(R.id.callbutton);
        FloatingActionButton msms=(FloatingActionButton)findViewById(R.id.smsbutton);
        FloatingActionButton mmail=(FloatingActionButton)findViewById(R.id.mailbutton);
        final LinearLayout mcalllayout= (LinearLayout)findViewById(R.id.calllayout);
        final LinearLayout msmslayout= (LinearLayout)findViewById(R.id.smslayout);
        final LinearLayout mmaillayout= (LinearLayout)findViewById(R.id.maillayout);
        final Animation mshowButton= AnimationUtils.loadAnimation(aboutus.this,R.anim.show_button);
        final Animation mhideButton= AnimationUtils.loadAnimation(aboutus.this,R.anim.hide_button);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mcalllayout.getVisibility()==View.VISIBLE && msmslayout.getVisibility()==View.VISIBLE && mmaillayout.getVisibility()==View.VISIBLE){
                    mcalllayout.setVisibility(View.GONE);
                    msmslayout.setVisibility(View.GONE);
                    mmaillayout.setVisibility(View.GONE);
                    mFab.startAnimation(mhideButton);
                }else
                {
                    mcalllayout.setVisibility(View.VISIBLE);
                    msmslayout.setVisibility(View.VISIBLE);
                    mmaillayout.setVisibility(View.VISIBLE);
                    mFab.startAnimation(mshowButton);
                }

            }
        });
        mcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent= new Intent(Intent.ACTION_DIAL);
                mIntent.setData(Uri.parse("tel:"+phone));
                if(mIntent.resolveActivity(getPackageManager())!= null)
                {
                    startActivity(mIntent);
                }else{
                    Toast.makeText(aboutus.this,"There is no app support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });
        msms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent= new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse("sms:"+phone));
                if(mIntent.resolveActivity(getPackageManager())!= null)
                {
                    startActivity(mIntent);
                }else{
                    Toast.makeText(aboutus.this,"There is no app support this action",Toast.LENGTH_SHORT).show();
                }

            }
        });
        mmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setType("text/email");
                mIntent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"sonali17196@gmail.com"});  //developer 's email
                mIntent.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                mIntent.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "");  //Email 's Greeting text
                if (mIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(mIntent, "Send Feedback:"));
                } else {
                    Toast.makeText(aboutus.this, "There is no app support this action", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}


