package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterErrors implements Parcelable {

    @SerializedName("errors")
    @Expose
    private Errors errors;

    public RegisterErrors() {
    }

    protected RegisterErrors(Parcel in) {
        errors = in.readParcelable(Errors.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(errors, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterErrors> CREATOR = new Creator<RegisterErrors>() {
        @Override
        public RegisterErrors createFromParcel(Parcel in) {
            return new RegisterErrors(in);
        }

        @Override
        public RegisterErrors[] newArray(int size) {
            return new RegisterErrors[size];
        }
    };

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public RegisterErrors(Errors errors) {
        this.errors = errors;
    }

}