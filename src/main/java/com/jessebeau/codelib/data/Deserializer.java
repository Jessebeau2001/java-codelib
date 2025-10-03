package com.jessebeau.codelib.data;

public interface Deserializer<T, V, R> {
    R deserialize(T type, V value) throws UnsupportedMappingException;

    /**
     * Adds a mapping for the provided name to the given class instance.
     *
     * @param type the type name to add the mapping for
     * @param mapping class instance to map the name to
     */
    void addMapping(String type, Class<? extends R> mapping);

    /**
     * Adds a mapping for the provided class based on the class's simple name by
     * delegating the call to interface member {@code addMapping()}.
     *
     * @param clazz instance class of the class to add the mapping for
     * @see #addMapping(String, Class)
     * @apiNote this method does not work for jars that are obfuscated
     */
    default void addSimpleMapping(Class<? extends R> clazz) {
        addMapping(clazz.getName(), clazz);
    }

    class UnsupportedMappingException extends RuntimeException {
        private final String name;

        public UnsupportedMappingException(String name) {
            super("Mapping " + name + "is not supported");
            this.name = name;
        }

        public String name() {
            return name;
        }
    }
}
