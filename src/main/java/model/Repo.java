package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public  class Repo {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;
    }
