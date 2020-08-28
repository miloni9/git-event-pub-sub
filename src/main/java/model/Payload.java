package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Payload {

    @SerializedName("issue")
    private Issue issue;
    @SerializedName("action")
    private String action;
    @SerializedName("comment")
    private Comment comment;
    @SerializedName("push_id")
    private long pushId;
    @SerializedName("size")
    private int size;
    @SerializedName("distinct_size")
    private int distinctSize;
    @SerializedName("ref")
    private String ref;
    @SerializedName("head")
    private String head;
    @SerializedName("before")
    private String before;
    @SerializedName("commits")
    private List<Commits> commits;
}
