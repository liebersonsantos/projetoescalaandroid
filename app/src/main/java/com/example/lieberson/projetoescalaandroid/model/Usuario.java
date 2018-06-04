package com.example.lieberson.projetoescalaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Parcelable {

    private long idUsuario;

    @SerializedName("nameUser")
    private String nome;

    @SerializedName("emailUser")
    private String email;

    @SerializedName("phoneUser")
    private String telefone;

    @SerializedName("passwordUser")
    private String senha;

    @SerializedName("resultado")
    private String resultado;

    public Usuario() {
    }

    public Usuario(long idUsuario, String nome, String email, String telefone, String senha, String resultado) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.resultado = resultado;
    }

    protected Usuario(Parcel in) {  //serializa e deserializa
        idUsuario = in.readLong();
        nome = in.readString();
        email = in.readString();
        telefone = in.readString();
        senha = in.readString();
        resultado = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idUsuario);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(telefone);
        dest.writeString(senha);
        dest.writeString(resultado);
    }
}