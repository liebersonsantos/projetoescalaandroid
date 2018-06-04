package com.example.lieberson.projetoescalaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginError implements Parcelable{


    @SerializedName("errors")
    @Expose
    private Errors errors;

    @SerializedName("code")
    @Expose
    private boolean code;

    @SerializedName("result")
    @Expose
    private String result;

    public LoginError() {
    }

    public LoginError(Errors errors, boolean code, String result) {
        this.errors = errors;
        this.code = code;
        this.result = result;
    }

    protected LoginError(Parcel in) {
        errors = in.readParcelable(Errors.class.getClassLoader());
        code = in.readByte() != 0;
        result = in.readString();
    }

    public static final Creator<LoginError> CREATOR = new Creator<LoginError>() {
        @Override
        public LoginError createFromParcel(Parcel in) {
            return new LoginError(in);
        }

        @Override
        public LoginError[] newArray(int size) {
            return new LoginError[size];
        }
    };

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(errors, flags);
        dest.writeByte((byte) (code ? 1 : 0));
        dest.writeString(result);
    }
}
