
package com.example.jobscheduller_.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobSchedullerModel implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<JobSchedullerModel> CREATOR = new Creator<JobSchedullerModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public JobSchedullerModel createFromParcel(Parcel in) {
            return new JobSchedullerModel(in);
        }

        public JobSchedullerModel[] newArray(int size) {
            return (new JobSchedullerModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1472727283692102691L;

    protected JobSchedullerModel(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public JobSchedullerModel() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public JobSchedullerModel(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
