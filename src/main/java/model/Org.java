package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Org {

    @SerializedName("id")
    private int id;
    @SerializedName("login")
    private String login;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;
}