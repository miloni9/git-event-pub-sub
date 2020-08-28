package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Event {

    @SerializedName("actor")
    private Actor actor;
    @SerializedName("public")
    private boolean publicX;
    @SerializedName("payload")
    private Payload payload;
    @SerializedName("repo")
    private Repo repo;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private long id;
    @SerializedName("type")
    private String type;
}
