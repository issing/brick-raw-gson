package net.isger.raw;

import java.io.IOException;

import net.isger.util.reflect.Converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GsonAdapter extends TypeAdapter<Object> {

    public static final TypeAdapterFactory FACTORY;

    private Gson gson;

    private Class<?> target;

    static {
        FACTORY = new TypeAdapterFactory() {
            @SuppressWarnings("unchecked")
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                Class<?> rawType = type.getRawType();
                if (!Converter.isSupport(rawType)) {
                    return null;
                }
                return (TypeAdapter<T>) new GsonAdapter(gson, rawType);
            }
        };
    }

    private GsonAdapter(Gson gson, Class<?> target) {
        this.gson = gson;
        this.target = target;
    }

    public void write(JsonWriter out, Object value) throws IOException {
    }

    public Object read(JsonReader in) throws IOException {
        return Converter.convert(target, gson.getAdapter(Object.class).read(in));
    }
}
