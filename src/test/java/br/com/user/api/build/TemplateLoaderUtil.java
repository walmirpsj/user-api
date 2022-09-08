package br.com.user.api.build;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

import java.net.URI;
import java.util.UUID;
import java.util.stream.IntStream;

public class TemplateLoaderUtil {

    public static void load() {
        FixtureFactoryLoader.loadTemplates("br.com.user.api.build");
    }

    public static Object[] randomString(String prefix, Integer n) {
        return IntStream.range(1, n).mapToObj(v -> prefix + v).toArray(String[]::new);
    }

    public static Object[] randomUUID(Integer n) {
        return IntStream.range(1, n).mapToObj(i -> UUID.randomUUID().toString()).toArray(String[]::new);
    }

    public static Object[] randomUri(String prefix, Integer n) {
        return IntStream.range(1, n).mapToObj(v -> URI.create(prefix + v)).toArray(URI[]::new);
    }
}
