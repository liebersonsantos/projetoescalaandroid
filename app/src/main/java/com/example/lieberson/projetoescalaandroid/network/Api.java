package com.example.lieberson.projetoescalaandroid.network;

import com.example.lieberson.projetoescalaandroid.model.Login;
import com.example.lieberson.projetoescalaandroid.model.LoginResponse;
import com.example.lieberson.projetoescalaandroid.model.LoginToken;
import com.example.lieberson.projetoescalaandroid.model.Usuario;

import io.reactivex.Single;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @POST("wsCreateUser")
    Call<Usuario> createUser(@Body Usuario usuario);
//    Single<retrofit2.Response<Void>> createUser(@Body Usuario usuario);    //Single => Dessa forma trabalhando reativamente-----será apenas um obj

    @GET("wsForgotPassword")
    Single<Response> forgotPassword(String email);

//    @GET("wsLogin")
//    Call<ResponseBody> login(@Body String email, String senha);
    @POST("wsLogin")
    Single<LoginToken> login(@Body Login login);

    @PUT("wsUpdateUser")
    Single<Response> updateUser(String token, @Body Usuario usuario); //sempre que eu passar um objeto, eu uso @body para pegar o obj e transforma em um json


}
