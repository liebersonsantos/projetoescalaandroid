package br.com.escala.app.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.shaishavgandhi.loginbuttons.FacebookButton;
import com.shaishavgandhi.loginbuttons.GoogleButton;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import br.com.escala.app.BuildConfig;
import br.com.escala.app.R;
import br.com.escala.app.adapters.AdapterLogin;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.Preferencias;
import br.com.escala.app.helper.SHA1;
import br.com.escala.app.model.Login;
import br.com.escala.app.model.LoginResponse;
import br.com.escala.app.model.MagazineRespose;
import br.com.escala.app.model.Revista;
import br.com.escala.app.model.Usuario;
import br.com.escala.app.network.RestClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextView textCadastro;
    private AppCompatEditText senhaLogin;
    private AppCompatEditText emailLogin;
    private Button botaoLogar;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private List<Revista> revistas;
    private AdapterLogin adapterLogin;
    private Dialog dialog;

    private android.support.v7.widget.Toolbar toolbar;

    private GoogleApiClient googleApiClient;
    private GoogleButton botaoGoogle;
    public static final int SIGN_IN_CODE = 777;
    private Preferencias preferencias;

    private FacebookButton loginFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Login login;
    private String senhaCodificada;
    private Usuario usuario;
    static String intercEmail, intercSenha;

    private CompositeDisposable disposable = new CompositeDisposable();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencias = new Preferencias(this);

        progressDialog = new ProgressDialog(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

//        toolbar = findViewById(R.id.toolbarId);
//        toolbar.inflateMenu(R.menu.menu_toolbar);

        initViews();
        settingsAdapter();
        getWsMagazines();

        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    user.getIdToken(true);

//                    goCadastro(Constantes.LOGINFACEBOOK);
                }

            }
        };

        initGoogle();

        logarFacebook();

        logarComGoogle();

        botaoLogar();


        textCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void settingsAdapter() {

        adapterLogin = new AdapterLogin();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterLogin);
    }

    private void logarFacebook() {

        loginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                System.out.println("ON SUCESS");

                                handleFacebookAccesToken(loginResult.getAccessToken());

                                String tokenFacebook = String.valueOf(AccessToken.getCurrentAccessToken().getToken());
                                String idUserFacebook = AccessToken.getCurrentAccessToken().getUserId();
                                System.out.println("TOKENFACE ==> " + tokenFacebook);
                                System.out.println("IDUSERFACEBOOK ==> " + idUserFacebook);

                            }

                            @Override
                            public void onCancel() {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                System.out.println("ON ERROR");
                            }
                        });
            }
        });
    }

    private void handleFacebookAccesToken(final AccessToken accessToken) {

        final AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                System.out.println("TOKEN FACE ==> " + authCredential);

                if (!task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "ERRO AO LOGAR COM FACABOOK", Toast.LENGTH_SHORT).show();

                } else {
                    goCadastro(Constantes.LOGINFACEBOOK);
                }
            }
        });
    }
//    private void getDataFace() {
//
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
//    }

    private void logarComGoogle() {

        botaoGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    private void initGoogle() {

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void initViews() {

        emailLogin = findViewById(R.id.edit_text_login_id);
        senhaLogin = findViewById(R.id.edit_senha_id);
        textCadastro = findViewById(R.id.text_faca_cadastro);
        botaoLogar = findViewById(R.id.btn_logar_id);
        recyclerView = findViewById(R.id.recyclerView_id);

//        revistas = gerarDados(13);

        botaoGoogle = findViewById(R.id.btn_google_id);
//        botaoGoogle.setSize(SignInButton.SIZE_ICON_ONLY);

        loginFacebook = findViewById(R.id.login_buttonF_id);
    }

    private void getWsMagazines() {

        RestClient.getInstance().magazines().enqueue(new Callback<MagazineRespose>() {
            @Override
            public void onResponse(Call<MagazineRespose> call, Response<MagazineRespose> response) {

                if (response.isSuccessful() && response.body() != null){

                    adapterLogin.setRevistaList(response.body().getRevistas());
                }else {

                    System.out.println("ERRO ao carregar as imagens");
                }

            }

            @Override
            public void onFailure(Call<MagazineRespose> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "Erro ao carregar Dados", Toast.LENGTH_SHORT).show();
            }
        });

    }





//    private List<Revista> gerarDados(int quant) {
//
//       List<Revista> revistas = new ArrayList<>();
//
//        for (int i = 0; i < quant; i++) {
//
//            Revista revista1 = new Revista();
//            revista1.setId(i);
//            revista1.setCategoria("categoria " + i);
//            revista1.setDataLancamento("00/00/00");
//            revista1.setNomeRevista("nome " + i);
//            revista1.setDescricao("descricao " + i);
//            revista1.setImage("https://cdnstatic8.com/temporalcerebral.com.br/wp-content/uploads/2015/10/5-dicas-design-de-capas-revistas-1-gq.jpg");
//            revista1.setUrlPdf("/scielobooks/38m/pdf/santos-9788523209087.pdf");
//
//            revistas.add(revista1);
//
//        }
//
//        return revistas;
//    }

    private void botaoLogar() {

        botaoLogar.setOnClickListener(v -> {

                usuario = new Usuario();

                String email = emailLogin.getText().toString().trim();

                try {
                    senhaCodificada = SHA1.codandoSHA1(senhaLogin.getText().toString().trim());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                usuario.setEmail(email);
                usuario.setSenha(senhaCodificada);

                /*SETTANDO LOGIN*/
                login = new Login();
                login.setToken("");
                login.setType(Constantes.LOGINCOMUM);
                login.setVersion((float) BuildConfig.VERSION_CODE);

                preferencias.clearPreferences();
//                preferencias.salvarDados("", "", "");
                preferencias.salvarDados(email, senhaCodificada, "");



//            preferencias.salvarDados(email, senhaCodificada, loginResponse.getToken());

            disposable.add(RestClient.getInstanceLogin(LoginActivity.this).login(login)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable1 -> {
                        // colocar o loading para a tela

                        progressDialog.setMessage("Carregando Dados...");
                        progressDialog.show();

                    })
                    .doAfterTerminate(() -> {
                        // tirar o loading pra ma tela

                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                    })
                    .doOnError(throwable -> {

                        if (throwable instanceof HttpException){

                            HttpException exception = (HttpException) throwable;
                            LoginResponse loginResponse = clearErrorsLoginResponse(exception.response());
                            Log.i("TAG", "doOnError: " + loginResponse.getErrors().getError());
                            // mostrar a mensagem do servidor pro usuario

                            Toast.makeText(LoginActivity.this, loginResponse.getErrors().getError(), Toast.LENGTH_LONG).show();
                        }

                        Log.i("TAG", "botaoLogarDoOnError: " + throwable.getMessage());

                    })
                    .subscribe(loginResponse -> {
                        Log.i("TAG", "onResponse: " + loginResponse.getToken());
                        // liberar a tela pro usuario e ir para home

                        Toast.makeText(LoginActivity.this, "Login realizado com Sucesso", Toast.LENGTH_LONG).show();

                        preferencias.salvarDados(usuario.getEmail(), usuario.getSenha(), loginResponse.getToken());

                        Intent intent = new Intent(this, ApresentacaoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }, throwable -> {
                        Log.i("TAG", "throwable: " + throwable.getMessage());
                        // mostrar a mensagem pro usuario, de error

                    })
            );
        });
    }

    private LoginResponse clearErrorsLoginResponse(Response<?> response) {
        Gson gson = new Gson();
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (response.errorBody() != null) {
                String res = response.errorBody().string();
                loginResponse = gson.fromJson(res, LoginResponse.class);
                return loginResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginResponse;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        System.out.println("RESULT " + result.getStatus());

        if (result.isSuccess()) {

            goCadastro(Constantes.LOGINGOOGLE);
        } else {

            Toast.makeText(this, "Erro ao iniciar sessão", Toast.LENGTH_SHORT).show();
        }
    }

    private void goCadastro(int tipo) {

        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("TIPO", tipo);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(authStateListener);

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//
//        if (isLoggedIn){
//
//            System.out.println("TOKENFACEBOOK ---> " + accessToken);

//            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//            Intent intent = new Intent(LoginActivity.this, ApresentacaoActivity.class);
//            startActivity(intent);
//            finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAuth.removeAuthStateListener(authStateListener);
        disposable.dispose();

    }
}
