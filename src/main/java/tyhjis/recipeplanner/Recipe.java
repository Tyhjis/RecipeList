package tyhjis.recipeplanner;

import sun.reflect.Reflection;

/**
 * Created by khansson on 17/05/2017.
 */
public class Recipe extends DBObject {
    private String url;
    private String table;
    private long categoryId;
    private long id;

    public Recipe() {
        super("recipes");
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return this.id + " : " + this.url + " : " + this.categoryId;
    }

    @Override
    public String getKeys() {
        return String.format("({}", "url").toString();
    }

    @Override
    public String getValues() {
        return null;
    }
}
