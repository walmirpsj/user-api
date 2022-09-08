package br.com.user.api.integration.resource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class GitHubUserResponse {
    private String id;
    private String login;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String name;
    private String company;
    private String location;
    private String bio;
    private String followers;
    private String following;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private String createdAt;
}
