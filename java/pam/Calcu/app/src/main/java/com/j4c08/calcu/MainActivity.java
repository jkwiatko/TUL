package com.j4c08.calcu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.simple)
    public void onClickSimple(Button simple) {
        Intent intent = new Intent(this, Base.class);
        startActivity(intent);
    }

    @OnClick(R.id.advanced)
    public void onClickAdvanced(Button advanced) {
        Intent intent = new Intent(this, Advanced.class);
        startActivity(intent);
    }


    @OnClick(R.id.about)
    public void onClickAbout(Button about) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }


    @OnClick(R.id.exit)
    public void onClickExit(Button exit) {
        finish();
    }
}
