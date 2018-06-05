package com.example.lieberson.projetoescalaandroid.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.helper.Constantes;
import com.example.lieberson.projetoescalaandroid.helper.SHA1;
import com.example.lieberson.projetoescalaandroid.model.RegisterErrors;
import com.example.lieberson.projetoescalaandroid.model.Usuario;
import com.example.lieberson.projetoescalaandroid.network.RestClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

public class CadastroActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button botaoCriarConta;
    private EditText cadNome, cadEmail, cadConfirmaEmail, cadFone, cadSenha, cadConfirmaSenha;
    private String idUsuarioGoogle;
    private String token;
    private String grantedScopes;
    private String senhaCodificada;
    private int tipo;
    private ProgressDialog progressDialog;

    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private Usuario usuario;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.activity_cadastro);

        initViews();

        mAuth = FirebaseAuth.getInstance();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                tipo = extras.getInt("TIPO");
            }
            if (tipo == Constantes.LOGINFACEBOOK) {
                System.out.println("LOGINFACEBOOK OK");
                initFacebook();
            } else if (tipo == Constantes.LOGINGOOGLE) {
                System.out.println("LOGINGOOGLE OK");
                initGoogle();
            } else {
                System.out.println("LOGINCOMUM");
            }

        }

        criarConta();
    }

    private void initFacebook() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        System.out.println(firebaseUser.getMetadata());
        if (firebaseUser != null) {

            String nome = firebaseUser.getDisplayName();
            String email = firebaseUser.getEmail();

            System.out.println("MMMMMMMMMMMMMMMMMMMMM  " + nome + email);

            cadNome.setText(nome);
            cadEmail.setText(email);
            cadConfirmaEmail.setText(email);
        } else {

            System.out.println("ESSA MERDA ESTA VINDO NULA LA DA CASA DO GUINA");
        }
    }

    private void initGoogle() {

        System.out.println("================== ENTROU LOGIN GOOGLE ==============");

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        acessarDados();


    }

    @Override
    protected void onStart() {
        super.onStart();

//        acessarDados();
    }

    private void acessarDados() {

        /*ESSE METODO PERMITE, DE FORMA SILENCIOSA, ACESSAR OS DADOS DO USUARIO GOOGLE*/
        OptionalPendingResult<GoogleSignInResult> pendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if (pendingResult.isDone()) {

            GoogleSignInResult result = pendingResult.get();
            handleSignInResult(result);

        } else {
            pendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);

                }
            });
        }
    }

    private void criarConta() {

        botaoCriarConta.setOnClickListener((View v) -> {
            usuario = new Usuario();

            try {
                senhaCodificada = SHA1.codandoSHA1(cadSenha.getText().toString().trim());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            usuario.setNome(cadNome.getText().toString());
            usuario.setEmail(cadEmail.getText().toString());
            usuario.setTelefone(cadFone.getText().toString());
            usuario.setSenha(senhaCodificada);


            System.out.println("USUARIO " + usuario.getNome());
            System.out.println("EMAIL " + usuario.getEmail());
            System.out.println("SENHA " + usuario.getSenha());
            System.out.println("TELEFONE " + usuario.getTelefone());


//            RestClient.getInstance(CadastroActivity.this).createUser(usuario).enqueue(new Callback<Usuario>() {
//                @Override
//                public void onResponse(@NonNull retrofit2.Call<Usuario> call, @NonNull Response<Usuario> response) {
//
//                    if(response.code() == 200) {
//
//                        System.out.println("Status: "+response.body() +" Resultado: "+response.body().getResultado());
//                        Toast.makeText(CadastroActivity.this, "Usuário Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
//
//
//                    } else {
//
//                        Gson gson = new Gson();
//                        try {
//
//                            if (response.errorBody() != null){
//
//                                Errors error = gson.fromJson(response.errorBody().string(), Errors.class);
//                                Toast.makeText(CadastroActivity.this, error.getError(), Toast.LENGTH_LONG).show();
//
//
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<Usuario> call, Throwable t) {
//
//                    Log.i("TAG", "onFailure: " + t.getCause().toString());
//
//                }
//            });

            disposable.add(RestClient.getInstance().createUser(usuario)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable1 -> {
                        // colocar o loading para a tela

                        progressDialog.setMessage("Cadastrando Dados...");
                        progressDialog.show();

                    })
                    .doAfterTerminate(() -> {
                        // tirar o loading pra ma tela

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    })
                    .doOnError(throwable -> {

                        if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            RegisterErrors registerErrors = clearErrorsLoginResponse(exception.response());

                            Log.i("TAG", "Falha no cadastro: " + registerErrors.getErrors().get(0));
                            // mostrar a mensagem do servidor pro usuario

                            Toast.makeText(CadastroActivity.this, registerErrors.getErrors().get(0), Toast.LENGTH_LONG).show();
                        }

                    })
                    .subscribe(loginResponse -> {

                        // liberar a tela pro usuario e ir para home
                        if (loginResponse.code() != 200) {
                            RegisterErrors registerErrors = clearErrorsLoginResponse(loginResponse);
                            Toast.makeText(CadastroActivity.this, registerErrors.getErrors().get(0), Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("TAG", "criarConta: " + loginResponse.code());
                            Toast.makeText(CadastroActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                        }

                    }, throwable -> {

                        // mostrar a mensagem pro usuario, de error
                        Log.i("TAG", "criarConta: " + throwable.getMessage());

                    })
            );
        });
    }

    private void initViews() {


        botaoCriarConta = findViewById(R.id.btn_cad_criar_conta);
        cadNome = findViewById(R.id.edit_cad_name);
        cadEmail = findViewById(R.id.edit_cad_email);
        cadConfirmaEmail = findViewById(R.id.edit_cad_confirma_email);
        cadFone = findViewById(R.id.edit_cad_fone);
        cadSenha = findViewById(R.id.edit_cad_senha);
        cadConfirmaSenha = findViewById(R.id.edit_cad_confirma_senha);
        progressDialog = new ProgressDialog(this);
    }

    private void handleSignInResult(GoogleSignInResult result) {

//        idUsuarioGoogle = null;
//        System.out.println("RESULTADO " + result.toString());
        try {

            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                //   firebaseAuthGoogle(re);

                cadNome.setText(account.getDisplayName());
                cadEmail.setText(account.getEmail());
                cadConfirmaEmail.setText(account.getEmail());
//                idUsuarioGoogle = account.getId();
                token = account.getIdToken();
                System.out.println("NAME ---> " + account.getDisplayName());
                System.out.println("EMAIL ---> " + account.getEmail());
                System.out.println("TOKEN ---> " + token);
                System.out.println("ID ---> " + account.getId());
                System.out.println("ACCOUNT ---> " + account);
                grantedScopes = String.valueOf(account.getGrantedScopes());
                System.out.println("GRANTED ---> " + grantedScopes);


            }
        } catch (Exception e) {
            e.getLocalizedMessage();

        }

//        else {
//
//            goLogin();
//        }
    }

    private void firebaseAuthGoogle(GoogleSignInAccount inAccount) {

//        System.out.println("GOOGLETOKEN ---> " + inAccount.getIdToken());

        AuthCredential authCredential = GoogleAuthProvider.getCredential(inAccount.getIdToken(), null);


        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            System.out.println("SUCESSO");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            System.out.println("USer " + firebaseUser);

                        } else {

                            System.out.println("FALHA");
                        }
                    }
                });
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private RegisterErrors clearErrorsLoginResponse(Response<?> response) {
        Gson gson = new Gson();
        RegisterErrors registerErrors = new RegisterErrors();
        try {
            if (response.errorBody() != null) {
                String res = response.errorBody().string();
                registerErrors = gson.fromJson(res, RegisterErrors.class);
                return registerErrors;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registerErrors;
    }
}
