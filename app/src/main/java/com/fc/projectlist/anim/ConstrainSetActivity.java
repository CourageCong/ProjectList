package com.fc.projectlist.anim;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fc.projectlist.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConstrainSetActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrain_set_one);
        constraintLayout = findViewById(R.id.anim_constrain);
        button = findViewById(R.id.anim_btn);
        textView = findViewById(R.id.txt);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                animToKeyFrame();
                Toast.makeText(ConstrainSetActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void animToKeyFrame(){
        ConstraintSet set = new ConstraintSet();
        set.clone(this, R.layout.activity_constrain_set_two);
        TransitionManager.beginDelayedTransition(constraintLayout);
        set.applyTo(constraintLayout);

        setContentData(this);
    }

    private void setContentData(final Activity activity) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... paramArrayOfParams) {
                InputStream is = activity.getResources().openRawResource(R.raw.code);
                StringBuffer response = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = null;
                try {
                    while ((s = br.readLine()) != null) {
                        response.append(s);
                        response.append("\n");
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                return response.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                textView.setText(result);
            }

        }.execute(null, null, null);
    }
}
