package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Revistas implements Parcelable{

    @SerializedName("")
    private long id;
    @SerializedName("")
    private String categoria;
    @SerializedName("")
    private String descricao;
    @SerializedName("")
    private String nomeRevista;
    @SerializedName("")
    private String dataLancamento;
    @SerializedName("")
    private String url_revista;

    public Revistas() {
    }

    public Revistas(long id, String categoria, String descricao, String nomeRevista,String dataLancamento, String url_revista) {
        this.id = id;
        this.categoria = categoria;
        this.descricao = descricao;
        this.nomeRevista = nomeRevista;
        this.dataLancamento = dataLancamento;
        this.url_revista = url_revista;
    }

    protected Revistas(Parcel in) {
        id = in.readLong();
        categoria = in.readString();
        descricao = in.readString();
        dataLancamento = in.readString();
        nomeRevista = in.readString();
        url_revista = in.readString();
    }

    public static final Creator<Revistas> CREATOR = new Creator<Revistas>() {
        @Override
        public Revistas createFromParcel(Parcel in) {
            return new Revistas(in);
        }

        @Override
        public Revistas[] newArray(int size) {
            return new Revistas[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getNomeRevista() {
        return nomeRevista;
    }

    public void setNomeRevista(String nomeRevista) {
        this.nomeRevista = nomeRevista;
    }

    public String getUrl_revista() {
        return url_revista;
    }

    public void setUrl_revista(String url_revista) {
        this.url_revista = url_revista;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(categoria);
        dest.writeString(descricao);
        dest.writeString(dataLancamento);
        dest.writeString(nomeRevista);
        dest.writeString(url_revista);
    }
}
