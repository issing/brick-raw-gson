package net.isger.raw;

import java.io.InputStreamReader;

import net.isger.util.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

public class GsonArtifact extends AbstractArtifact {

    private static final Logger LOG;

    private GsonBuilder builder;

    static {
        LOG = LoggerFactory.getLogger(GsonArtifact.class);
    }

    public GsonArtifact(Raw raw) {
        super(raw);
        this.builder = new GsonBuilder();
        this.builder.registerTypeAdapterFactory(GsonAdapter.FACTORY);
    }

    public Object use(String id, Object... args) {
        Object result = null;
        if (id.equalsIgnoreCase("transform")) {
            result = args != null && args.length > 1 ? transform((Class<?>) args[0], (String) args[1]) : transform();
        }
        return result;
    }

    public Object transform() {
        return transform(Object.class, null);
    }

    public <T> T transform(Class<T> type, String charsetName) {
        T result = null;
        Raw raw = getRaw();
        InputStreamReader reader = null;
        try {
            if (charsetName == null) {
                reader = new InputStreamReader(raw.getInputStream());
            } else {
                reader = new InputStreamReader(raw.getInputStream(), charsetName);
            }
            result = this.builder.create().fromJson(reader, type);
        } catch (Exception e) {
            LOG.warn("(!) Unable to transform [{}]", raw.getSource(), e.getCause());
        } finally {
            Files.close(reader);
        }
        return result;
    }
}
