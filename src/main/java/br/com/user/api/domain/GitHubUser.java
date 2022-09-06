package br.com.user.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {
    private String login;
    private String id;
    private String htmlUrl;
    private String name;
    private String company;
    private String location;
    private String bio;
    private String followers;
    private String following;
    private String avatarUrl;
    private String createdAt;
}
