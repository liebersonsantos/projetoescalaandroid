package br.com.escala.app.network;

import java.util.List;

import br.com.escala.app.model.Login;
import br.com.escala.app.model.LoginToken;
import br.com.escala.app.model.Revista;
import br.com.escala.app.model.Usuario;
import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface Api {

    @POST("wsCreateUser")
//    Call<Usuario> createUser(@Body Usuario usuario);
    Single<retrofit2.Response<Void>> createUser(@Body Usuario usuario);    //Single => Dessa forma trabalhando reativamente-----será apenas um obj

    @GET("wsForgotPassword")
    Single<Response> forgotPassword(String email);

//    @GET("wsLogin")
//    Call<ResponseBody> login(@Body String email, String senha);
    @POST("wsLogin")
    Single<LoginToken> login(@Body Login login);

    @PUT("wsUpdateUser")
    Single<Response> updateUser(String token, @Body Usuario usuario); //sempre que eu passar um objeto, eu uso @body para pegar o obj e transforma em um json

    @Streaming
    @Headers({"Content-Type: application/x-www-form-urlencoded", "Connection: keep-alive", "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36 OPR/46.0.2597.57", "Cache-Control: max-age=640000"})
    @GET("/{path}")
    Single<ResponseBody> downloadFileWithDynamicUrlSync(@Path("path") String path);

    @GET("wsMagazines")
    Call<List<Revista>> Magazines();


}
