package com.example.lieberson.projetoescalaandroid.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "preferencias.login";
    private SharedPreferences.Editor editor;

    private final String CHAVE_EMAIL = "emailLogado";
    private final String CHAVE_SENHA = "senhaLogada";
    private final String CHAVE_TOKEN = "tokenLogado";

    public Preferencias( Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void salvarDados( String emailLogado, String senhaLogada, String tokenLogado){

        editor.putString(CHAVE_EMAIL, emailLogado);
        editor.putString(CHAVE_SENHA, senhaLogada);
        editor.putString(CHAVE_TOKEN, tokenLogado);
        editor.commit();

    }

    public String getEmail(){
        return preferences.getString(CHAVE_EMAIL, null);
    }

    public String getSenha(){
        return preferences.getString(CHAVE_SENHA, null);
    }

    public String getToken(){return preferences.getString(CHAVE_TOKEN, null);   }

    public void clearPreferences(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(contexto);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.clear();
        editor1.commit();
    }



}
