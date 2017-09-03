package cn.com.chinatelecom.ctdm.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class ClientConf<T extends ClientConf>
        implements Iterable<Map.Entry<String, String>> {

    public interface ConfEntry {
        String key();

        Object dflt();
    }

    protected final ConcurrentMap<String, String> config;

    protected ClientConf(Properties props) {
        config = new ConcurrentHashMap<>();
        if (props != null) {
            for (String key: props.stringPropertyNames()) {
                config.put(key, props.getProperty(key));
            }
        }
    }

    public String get(String key) {
        return config.get(key);
    }

    @SuppressWarnings("unchecked")
    public T set(String key, String value) {
        config.put(key, value);
        return (T) this;
    }

    public String get(ConfEntry e) {
        Object value = get(e, String.class);
        return (String) (value != null ? value : e.dflt());
    }

    public boolean getBoolean(ConfEntry e) {
        String val = get(e, Boolean.class);
        if (val != null) {
            return Boolean.parseBoolean(val);
        } else {
            return (Boolean) e.dflt();
        }
    }

    public int getInt(ConfEntry e) {
        String val = get(e, Integer.class);
        if (val != null) {
            return Integer.parseInt(val);
        } else {
            return (Integer) e.dflt();
        }
    }

    public long getLong(ConfEntry e) {
        String val = get(e, Long.class);
        if (val != null) {
            return Long.parseLong(val);
        } else {
            return (Long) e.dflt();
        }
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return config.entrySet().iterator();
    }

    @SuppressWarnings("unchecked")
    public T set(ConfEntry e, Object value) {
        check(typesMatch(value, e.dflt()), "Value doesn't match configuration entry type for %s.",
                e.key());
        if (value == null) {
            config.remove(e.key());
        } else {
            config.put(e.key(), value.toString());
        }
        return (T) this;
    }

    private String get(ConfEntry e, Class<?> requestedType) {
        check(getType(e.dflt()).equals(requestedType), "Invalid type conversion requested for %s.",
                e.key());
        return config.get(e.key());
    }

    private boolean typesMatch(Object test, Object expected) {
        return test == null || getType(test).equals(getType(expected));
    }

    private Class<?> getType(Object o) {
        return (o != null) ? o.getClass() : String.class;
    }

    private void check(boolean test, String message, Object... args) {
        if (!test) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }
}
