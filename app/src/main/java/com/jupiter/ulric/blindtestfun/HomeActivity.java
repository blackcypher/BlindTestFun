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

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("       Fun Blind Test");
        setSupportActionBar(toolbar);


        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        Button fab = (Button) findViewById(R.id.actionRandom);

        /*Typeface font = Typeface.createFromAsset(getAssets(), "Helv Neue 67 Med Cond.ttf");
        fab.setTypeface(font);*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RandomPlayActivity.class);
                startActivity(i);
            }
        });
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_home, container, false);
            return rootView;
        }
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Users> {
        @Override
        protected Users doInBackground(Void... params) {
            try {
                //final String url = "http://rest-service.guides.spring.io/greeting";
                final String url = "http://89.86.60.41/api/v1/users/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Users users = restTemplate.getForObject(url, Users.class);
                return users;
            } catch (Exception e) {
                Log.e("HomeActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Users users) {
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(users.getId());
            greetingContentText.setText(users.getEmail());
        }

    }*/
    private void startProfilActivity(){
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
        System.out.println(" id === "+item.getItemId());
        if (item.getItemId() == R.id.miProfile) {
            startProfilActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

