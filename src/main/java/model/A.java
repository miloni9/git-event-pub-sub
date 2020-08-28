package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class A {

    @SerializedName("actor")
    private Actor actor;

    public static class Actor {

        @SerializedName("map")
        private Map map;

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public class Map {

            @SerializedName("display_login")
            private String displayLogin;
            @SerializedName("avatar_url")
            private String avatarUrl;
            @SerializedName("id")
            private int id;
            @SerializedName("login")
            private String login;
            @SerializedName("gravatar_id")
            private String gravatarId;
            @SerializedName("url")
            private String url;
        }
    }
}
