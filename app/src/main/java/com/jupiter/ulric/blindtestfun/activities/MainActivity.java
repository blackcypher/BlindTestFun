package com.jupiter.ulric.blindtestfun.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.jupiter.ulric.blindtestfun.R;
import com.jupiter.ulric.blindtestfun.backgrounds.rotatingBackground.RotatingImageView;
import com.jupiter.ulric.blindtestfun.backgrounds.starAnimationView.StarAnimationView;
import com.jupiter.ulric.blindtestfun.model.Player;

import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private LinearLayout layout;
    private LinearLayout layoutStar;

    private LoginButton fcbButton;
    private Button mPartieRapide;
    private Button mSeConnecter;

    private EditText emailEditText;

    private Player users;

    private EditText tEmail;

    private CallbackManager callbackManager;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new Player();

        layout = (LinearLayout) findViewById(R.id.lin_background);
        layout.addView(new RotatingImageView(this));

        layoutStar = (LinearLayout) findViewById(R.id.lin_star);
        layoutStar.addView(new StarAnimationView(this));

        mPartieRapide = (Button) findViewById(R.id.actionRapide);
        mPartieRapide.setOnClickListener(this);

        mSeConnecter = (Button) findViewById(R.id.email_sign_in_button);
        mSeConnecter.setOnClickListener(this);

        emailEditText = (EditText) findViewById(R.id.emailEdit);
        emailEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailEditText.setFocusableInTouchMode(true);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        fcbButton = (LoginButton) findViewById(R.id.login_button);
        fcbButton.setReadPermissions("email");
        fcbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request =  GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {

                                if (response.getError() != null) {
                                    // handle error
                                } else {
                                    String user_email =response.getJSONObject().optString("email");
                                    users.setEmail(user_email);
                                    new HttpRequestTask().execute();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "last_name,first_name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast toast = Toast.makeText(getApplicationContext(), "on Cancel!", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast toast = Toast.makeText(getApplicationContext(), "erreur serveur", Toast.LENGTH_SHORT);
                System.out.println(exception.toString());
                toast.show();
            }
        });

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account != null) {
                //String personName = account.getDisplayName();
                String personEmail = account.getEmail();
                users.setEmail(personEmail);
            }
        } catch (ApiException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Connexion failed", Toast.LENGTH_SHORT);
            toast.show();
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Player> {
        @Override
        protected Player doInBackground(Void... params) {
            try {
                final String url = "http://89.86.60.41/api/v1/users";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Player usersResult = restTemplate.postForObject(url, users, Player.class);
                if(usersResult.getEmail()!=null){
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }

                return usersResult;
            } catch (Exception e) {
                Log.e("HomeActivity0", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Player users) {
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.actionRapide:
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                //Intent i = new Intent(MainActivity.this, TestActivity.class);
                startActivity(i);
                break;
            case R.id.email_sign_in_button:
                tEmail = (EditText) findViewById(R.id.emailEdit);
                if(tEmail!=null){
                    users.setEmail(tEmail.getText().toString());
                    new HttpRequestTask().execute();
                }
                break;
        }
    }
}