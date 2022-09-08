package br.com.user.api.build;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.user.api.build.ExampleType;
import br.com.user.api.repository.model.GitHubUserDB;
import br.com.user.api.repository.model.UserDB;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static br.com.user.api.build.ExampleType.VALID;
import static br.com.user.api.build.TemplateLoaderUtil.randomString;
import static br.com.user.api.build.TemplateLoaderUtil.randomUUID;
import static java.lang.String.format;

public class UserDBDataTestBuilder implements TemplateLoader {

    @Override
    public void load() {
        final var now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        Fixture.of(UserDB.class)
                .addTemplate(VALID.name(), new Rule() {
                    {
                        add("id", random(String.class, randomUUID(1000)));
                        add("name", random(randomString("User", 5)));
                        add("cpf", random(randomString("", 9)));
                        add("email", format("%s@teste.com.br", randomString("email", 5)));
                        add("phone", random(randomString("", 9)));

                        GitHubUserDB gitHubUserDB = GitHubUserDB.builder()
                                .id(Arrays.toString(randomUUID(1000)))
                                .bio(Arrays.toString(randomString("Bio ", 5)))
                                .build();
                        add("gitHubUser", gitHubUserDB);
                        add("createdDate", now);
                    }
                });
    }

    public static UserDB get(ExampleType exampleType) {
        return Fixture.from(UserDB.class).gimme(exampleType.name());
    }

    public static List<UserDB> get(Integer n, ExampleType exampleType) {
        return Fixture
                .from(UserDB.class)
                .gimme(n, exampleType.name());
    }

}
