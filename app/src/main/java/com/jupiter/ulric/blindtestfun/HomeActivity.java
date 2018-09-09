package com.jupiter.ulric.blindtestfun;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("       Fun Blind Test");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println(" id === " + item.getItemId());
                        return onOptionsItemSelected(item);
                    }
                });

        Button fab = (Button) findViewById(R.id.actionRandom);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RandomPlayActivity.class);
                startActivity(i);
            }
        });

    }

    private void startProfilActivity() {
        Intent i = new Intent(HomeActivity.this, ProfilActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(" id === " + item.getItemId());
        if (item.getItemId() == R.id.miProfile) {
            startProfilActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

