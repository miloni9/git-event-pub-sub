package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Commits {

    @SerializedName("sha")
    private String sha;
    @SerializedName("author")
    private Author author;
    @SerializedName("message")
    private String message;
    @SerializedName("distinct")
    private boolean distinct;
    @SerializedName("url")
    private String url;


}