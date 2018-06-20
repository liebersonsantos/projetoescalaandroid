package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MagazineContentsRelated implements Parcelable{

    @SerializedName("magazine_content_related_desc")
    private String magazineContentRelatedDesc;

    @SerializedName("magazine_content_related_img")
    private String magazineContentRelatedImg;

    protected MagazineContentsRelated(Parcel in) {
        magazineContentRelatedDesc = in.readString();
        magazineContentRelatedImg = in.readString();
    }

    public static final Creator<MagazineContentsRelated> CREATOR = new Creator<MagazineContentsRelated>() {
        @Override
        public MagazineContentsRelated createFromParcel(Parcel in) {
            return new MagazineContentsRelated(in);
        }

        @Override
        public MagazineContentsRelated[] newArray(int size) {
            return new MagazineContentsRelated[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(magazineContentRelatedDesc);
        dest.writeString(magazineContentRelatedImg);
    }

    public String getMagazineContentRelatedDesc() {
        return magazineContentRelatedDesc;
    }

    public void setMagazineContentRelatedDesc(String magazineContentRelatedDesc) {
        this.magazineContentRelatedDesc = magazineContentRelatedDesc;
    }

    public String getMagazineContentRelatedImg() {
        return magazineContentRelatedImg;
    }

    public void setMagazineContentRelatedImg(String magazineContentRelatedImg) {
        this.magazineContentRelatedImg = magazineContentRelatedImg;
    }


}
