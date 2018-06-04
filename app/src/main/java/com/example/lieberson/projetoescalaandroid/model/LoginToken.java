package com.example.lieberson.projetoescalaandroid.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;
public class LoginToken implements Parcelable {
    
	@SerializedName("token")
	@Expose
    private String token;
    
    public LoginToken() {}
    public LoginToken(String token){
        this.token = token;
    
    }
    public String getToken() {
		return token;
	}
	
    public void setToken(String token) {
		this.token = token;
	}
    
    protected LoginToken(Parcel in) {
        token = in.readString();
		
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(token);
                
    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LoginToken> CREATOR = new Parcelable.Creator<LoginToken>() {
        @Override
        public LoginToken createFromParcel(Parcel in) {
            return new LoginToken(in);
        }
        @Override
        public LoginToken[] newArray(int size) {
            return new LoginToken[size];
        }
    };
}