package com.fc.projectlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.annotation.HelloAnnotation;

@HelloAnnotation
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void annotationTest(){
        String str1="";
//        str1+=HelloWorld.hello1()+" ";
//        str1+=HelloWorld.hello2()+" ";
//        str1+=HelloWorld.hello3();
    }

}
