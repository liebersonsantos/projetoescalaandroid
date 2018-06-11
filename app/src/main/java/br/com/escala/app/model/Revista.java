package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Revista implements Parcelable{

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
    private String image;

    @SerializedName("")
    private String urlPdf;

    public Revista() {
    }


    protected Revista(Parcel in) {
        id = in.readLong();
        categoria = in.readString();
        descricao = in.readString();
        nomeRevista = in.readString();
        dataLancamento = in.readString();
        image = in.readString();
        urlPdf = in.readString();
    }

    public static final Creator<Revista> CREATOR = new Creator<Revista>() {
        @Override
        public Revista createFromParcel(Parcel in) {
            return new Revista(in);
        }

        @Override
        public Revista[] newArray(int size) {
            return new Revista[size];
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

    public String getNomeRevista() {
        return nomeRevista;
    }

    public void setNomeRevista(String nomeRevista) {
        this.nomeRevista = nomeRevista;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
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
        dest.writeString(nomeRevista);
        dest.writeString(dataLancamento);
        dest.writeString(image);
        dest.writeString(urlPdf);
    }
}
