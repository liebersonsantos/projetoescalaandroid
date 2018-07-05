package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Categories implements Parcelable{

    @SerializedName("magazine_cat_id")
    private String magazineCatId;
    @SerializedName("magazine_cat_name")
    private String magazineCatName;
    @SerializedName("magazine_cat_cover")
    private String magazineCatCover;

    public Categories() {
    }

    public Categories(String magazineCatId, String magazineCatName, String magazineCatCover) {
        this.magazineCatId = magazineCatId;
        this.magazineCatName = magazineCatName;
        this.magazineCatCover = magazineCatCover;
    }

    protected Categories(Parcel in) {
        magazineCatId = in.readString();
        magazineCatName = in.readString();
        magazineCatCover = in.readString();
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(magazineCatId);
        dest.writeString(magazineCatName);
        dest.writeString(magazineCatCover);
    }

    public String getMagazineCatId() {
        return magazineCatId;
    }

    public void setMagazineCatId(String magazineCatId) {
        this.magazineCatId = magazineCatId;
    }

    public String getMagazineCatName() {
        return magazineCatName;
    }

    public void setMagazineCatName(String magazineCatName) {
        this.magazineCatName = magazineCatName;
    }

    public String getMagazineCatCover() {
        return magazineCatCover;
    }

    public void setMagazineCatCover(String magazineCatCover) {
        this.magazineCatCover = magazineCatCover;
    }
}
