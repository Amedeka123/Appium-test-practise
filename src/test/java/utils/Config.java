// utils/Config.java (optional helper)
package utils;

import io.github.cdimascio.dotenv.Dotenv;

public final class Config {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

    private Config() {}

    public static String get(String key, String def) {
        String v = System.getProperty(key);
        if (v != null && !v.isEmpty()) return v;
        v = System.getenv(key);
        if (v != null && !v.isEmpty()) return v;
        v = DOTENV.get(key);
        return (v != null && !v.isEmpty()) ? v : def;
    }
}

