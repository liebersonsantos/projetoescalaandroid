package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MagazineCategoryResponse implements Parcelable{

    @SerializedName("status")
    private int status;
    @SerializedName("resultado")
    private String resultado;
    @SerializedName("revistas")
    private List<Revista> revistas;

    public MagazineCategoryResponse() {
    }

    public MagazineCategoryResponse(int status, String resultado, List<Revista> revistas) {
        this.status = status;
        this.resultado = resultado;
        this.revistas = revistas;
    }

    protected MagazineCategoryResponse(Parcel in) {
        status = in.readInt();
        resultado = in.readString();
        revistas = in.createTypedArrayList(Revista.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(resultado);
        dest.writeTypedList(revistas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MagazineCategoryResponse> CREATOR = new Creator<MagazineCategoryResponse>() {
        @Override
        public MagazineCategoryResponse createFromParcel(Parcel in) {
            return new MagazineCategoryResponse(in);
        }

        @Override
        public MagazineCategoryResponse[] newArray(int size) {
            return new MagazineCategoryResponse[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }
}
