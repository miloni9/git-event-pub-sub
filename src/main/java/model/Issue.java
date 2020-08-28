package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.json.JSONObject;

import java.util.List;

@Data
public class Issue {
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("author_association")
    private String authorAssociation;
    @SerializedName("number")
    private int number;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("performed_via_github_app")
    private Object performedViaGithubApp;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("active_lock_reason")
    private Object activeLockReason;
    @SerializedName("repository_url")
    private String repositoryUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("state")
    private String state;
    @SerializedName("locked")
    private boolean locked;
    @SerializedName("comments")
    private int comments;
    @SerializedName("closed_at")
    private String closedAt;
    @SerializedName("url")
    private String url;
    @SerializedName("milestone")
    private Object milestone;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("assignee")
    private Object assignee;
    @SerializedName("user")
    private User user;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("assignees")
    private List<JSONObject> assignees;
    @SerializedName("labels")
    private List<JSONObject> labels;
}