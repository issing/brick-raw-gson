package net.isger.raw;

import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

import net.isger.util.Files;

public class GsonArtifact implements Artifact {

    private static final Logger LOG;

    private GsonBuilder builder;

    static {
        LOG = LoggerFactory.getLogger(GsonArtifact.class);
    }

    private Raw raw;

    public GsonArtifact(Raw raw) {
        this.raw = raw;
        this.builder = new GsonBuilder();
        this.builder.registerTypeAdapterFactory(GsonAdapter.FACTORY);
    }

    public Raw getRaw() {
        return raw;
    }

    @SuppressWarnings("unchecked")
    public <T> T transform(Class<T> type) {
        return (T) transform((Type) type);
    }

    public Object transform(Type type) {
        Object result = null;
        Raw raw = getRaw();
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(raw.getInputStream());
            result = this.builder.create().fromJson(reader, type);
        } catch (Exception e) {
            LOG.warn("(!) Unable to transform [{}]", raw.getSource(),
                    e.getCause());
        } finally {
            Files.close(reader);
        }
        return result;
    }

}
