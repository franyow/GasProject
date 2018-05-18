package upc.pe.edu.gasprojectupc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.net.Authenticator;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    SignInButton signInButton;
    GoogleApiClient googleApiClient;
    TextView nameUser;
    GoogleSignInClient googleSignInClient;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        nameUser = (TextView) findViewById(R.id.nameSujetou);
        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInButton:
                signIn();
                break;
        }
    }

    private void signIn(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //updateUI(account);


            nameUser.setText("Hello: " + account.getDisplayName());
            Intent intent = new Intent(this,MainActivity.class  );
            startActivity(intent);


        }catch (ApiException e){
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }

       //if (result.isSuccess()){

       //    GoogleSignInAccount acct = result.getSignInAccount();

       //    nameUser.setText("Hello: " + acct.getDisplayName());

       //    Log.d("Holi boli", "Name: " + acct.getDisplayName());
       //    Log.d("Holi boli", "Family Name: " + acct.getFamilyName());
       //    Log.d("Holi boli", "Email: " + acct.getEmail());
       //    Log.d("Holi boli", "Given name: " + acct.getGivenName());
       //    Log.d("Holi boli", "Id: " + acct.getId());
       //    Log.d("Holi boli", "Token " + acct.getIdToken());
       //    Log.d("Holi boli", "Url Photo: " + acct.getPhotoUrl());
       //}else{
       //}
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }
}
