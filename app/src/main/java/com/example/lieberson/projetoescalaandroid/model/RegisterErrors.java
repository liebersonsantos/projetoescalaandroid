package com.example.lieberson.projetoescalaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterErrors implements Parcelable {

	@SerializedName("code")
	@Expose
	private int code;

	@SerializedName("errors")
	@Expose
    private List<String> errors;

    public RegisterErrors() {
    }

    public RegisterErrors(int code, List<String> errors) {
        this.code = code;
        this.errors = errors;
    }

    protected RegisterErrors(Parcel in) {
        code = in.readInt();
        errors = in.createStringArrayList();
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeStringList(errors);
    }
}