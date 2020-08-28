package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Comment {
    @SerializedName("author_association")
    private String authorAssociation;
    @SerializedName("issue_url")
    private String issueUrl;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("performed_via_github_app")
    private Object performedViaGithubApp;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private int id;
    @SerializedName("body")
    private String body;
    @SerializedName("user")
    private UserX user;
    @SerializedName("url")
    private String url;
    @SerializedName("node_id")
    private String nodeId;

}
