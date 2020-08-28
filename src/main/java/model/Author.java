package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Author {

    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;

}