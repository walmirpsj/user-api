package br.com.user.api.build;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.user.api.build.ExampleType;
import br.com.user.api.controller.resource.UserResource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static br.com.user.api.build.ExampleType.VALID;
import static br.com.user.api.build.TemplateLoaderUtil.randomString;
import static java.lang.String.format;

public class UserResourceDataTestBuilder implements TemplateLoader {

    @Override
    public void load() {
        final var now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        Fixture.of(UserResource.class)
                .addTemplate(VALID.name(), new Rule() {
                    {
                        add("name", random(randomString("User", 5)));
                        add("cpf", random(randomString("", 9)));
                        add("email", format("%s@teste.com.br", randomString("email", 5)));
                        add("phone", random(randomString("", 9)));
                        add("gitHubLogin", random(randomString("User", 5)));
                    }
                });
    }

    public static UserResource get(ExampleType exampleType) {
        return Fixture.from(UserResource.class).gimme(exampleType.name());
    }

    public static List<UserResource> get(Integer n, ExampleType exampleType) {
        return Fixture
                .from(UserResource.class)
                .gimme(n, exampleType.name());
    }

}
