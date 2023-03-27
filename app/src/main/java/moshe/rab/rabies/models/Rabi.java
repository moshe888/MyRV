package moshe.rab.rabies.models;

import java.util.HashMap;

public class Rabi {
    String key;
    String name;
    String url;
    String description;

    public Rabi(String name, String url, String description) {
        this.key = "";
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public Rabi(String key, String name, String url, String description) {
        this.key = key;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("name", name);
        map.put("url", url);
        map.put("description", description);
        return map;
    }

    public Rabi(HashMap<String, Object> map) {
        this.key = String.valueOf(map.get("key"));
        this.name = String.valueOf(map.get("name"));
        this.url = String.valueOf(map.get("url"));
        this.description = String.valueOf(map.get("description"));
    }

    public boolean isThereEmptyDataForEntered() {
        return name == null || name.trim().isEmpty() ||
                url == null || url.trim().isEmpty() ||
                description == null || description.trim().isEmpty();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
