package br.com.escala.app.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;
public class Errors implements Parcelable {
	
	@SerializedName("code")
	@Expose
	private int code;
    
	@SerializedName("error")
	@Expose
    private String error;
    
    public Errors() {}
    public Errors(int code, String error) {
        this.code = code;
    
        this.error = error;
    
    }
    public String getError() {
		return error;
	}
	
    public void setError(String error) {
		this.error = error;
	}
    
    public int getCode() {
		return code;
	}
	
    public void setCode(int code) {
		this.code = code;
	}
    
    protected Errors(Parcel in) {
        code = in.readInt();
		error = in.readString();
		
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeInt(code);
                
        dest.writeString(error);
                
    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Errors> CREATOR = new Parcelable.Creator<Errors>() {
        @Override
        public Errors createFromParcel(Parcel in) {
            return new Errors(in);
        }
        @Override
        public Errors[] newArray(int size) {
            return new Errors[size];
        }
    };
}