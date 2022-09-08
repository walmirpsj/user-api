package br.com.user.api.build;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.user.api.domain.GitHubUser;

import java.util.List;
import java.util.Optional;

import static br.com.user.api.build.ExampleType.VALID;
import static br.com.user.api.build.TemplateLoaderUtil.randomString;

public class GitHubUserDataTestBuilder implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(GitHubUser.class)
                .addTemplate(VALID.name(), new Rule() {
                    {
                        add("name", random(randomString("name", 5)));
                        add("login", random(randomString("login", 5)));
                        add("bio", random(randomString("Bio", 5)));
                    }
                });
    }

    public static GitHubUser get(ExampleType exampleType) {
        return Fixture.from(GitHubUser.class).gimme(exampleType.name());
    }

    public static List<GitHubUser> get(Integer n, ExampleType exampleType) {
        return Fixture
                .from(GitHubUser.class)
                .gimme(n, exampleType.name());
    }

    public static Optional<GitHubUser> getOptional(ExampleType exampleType) {
        return Optional.of(get(exampleType));
    }
}
