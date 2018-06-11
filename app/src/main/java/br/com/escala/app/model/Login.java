package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Login implements Parcelable{


    @SerializedName("token")
    private String token;

    @SerializedName("version")
    private Float version;

    @SerializedName("type")
    private int type;


    public Login() {
    }

    public Login(String token, Float version, int type) {
        this.token = token;
        this.version = version;
        this.type = type;
    }

    protected Login(Parcel in) {
        token = in.readString();
        if (in.readByte() == 0) {
            version = null;
        } else {
            version = in.readFloat();
        }
        type = in.readInt();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        if (version == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(version);
        }
        dest.writeInt(type);
    }
}
