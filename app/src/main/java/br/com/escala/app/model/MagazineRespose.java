package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MagazineRespose implements Parcelable{

    @SerializedName("status")
    private int status;
    @SerializedName("revistas")
    private List<Revista> revistas;

    protected MagazineRespose(Parcel in) {
        status = in.readInt();
        revistas = in.createTypedArrayList(Revista.CREATOR);
    }

    public static final Creator<MagazineRespose> CREATOR = new Creator<MagazineRespose>() {
        @Override
        public MagazineRespose createFromParcel(Parcel in) {
            return new MagazineRespose(in);
        }

        @Override
        public MagazineRespose[] newArray(int size) {
            return new MagazineRespose[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeTypedList(revistas);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }
}
