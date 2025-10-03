package com.jessebeau.codelib.data.json;

/*
import com.google.gson.Gson;
import com.jessebeau.codelib.data.Deserializer;

import java.util.HashMap;
import java.util.Map;

public final class JsonDeserializer<T> implements Deserializer<String, String, T> {
    private final Gson gson;
    private final Map<String, Class<? extends T>> typeMappings;

    public JsonDeserializer(Gson gson) {
        this.typeMappings = new HashMap<>();
        this.gson = gson;
    }

    public JsonDeserializer() {
        this(new Gson());
    }

    @Override
    public void addMapping(String type, Class<? extends T> mapping) {
        typeMappings.put(type, mapping);
    }

    public T deserialize(String typeName, String value) throws UnsupportedMappingException {
        var clazz = getMappingOrThrow(typeName);
        return gson.fromJson(value, clazz);
    }

    */
/**
     * Retrieved the class for the given mapping based on the name or throws if no mapping is found.
     *
     * @param typeName the name of the mapping
     * @throws UnsupportedMappingException when the deserializer has no mapping for the typename
     * @return the class for the given mapping
     *//*

    private Class<? extends T> getMappingOrThrow(String typeName) throws UnsupportedMappingException {
        var mapping = typeMappings.get(typeName);
        if (mapping == null) {
            throw new UnsupportedMappingException(typeName);
        }
        return mapping;
    }
}
*/
