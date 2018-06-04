package com.example.lieberson.projetoescalaandroid.model;

import android.os.Parcel;
import android.os.Parcelable;
public class LoginResponse implements Parcelable {
    
	private LoginToken loginToken;

	private Errors errors;

	public LoginResponse() {
	}

	public LoginResponse(LoginToken token, Errors errors) {
		this.loginToken = token;
		this.errors = errors;
	}

	protected LoginResponse(Parcel in) {
		loginToken = in.readParcelable(LoginToken.class.getClassLoader());
		errors = in.readParcelable(Errors.class.getClassLoader());
	}

	public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
		@Override
		public LoginResponse createFromParcel(Parcel in) {
			return new LoginResponse(in);
		}

		@Override
		public LoginResponse[] newArray(int size) {
			return new LoginResponse[size];
		}
	};

	public LoginToken getLoginToken() {
		return loginToken;
	}

	public void LoginToken(LoginToken token) {
		this.loginToken = token;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(loginToken, flags);
		dest.writeParcelable(errors, flags);
	}
}