package modelview;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String,Object> donnee;


    public void addItem(String nameValue, Object object) {
        donnee.put(nameValue, object);
    }
    
    public HashMap<String, Object> getDonnee() {
        return donnee;
    }

    public void setDonnee(HashMap<String, Object> donnee) {
        this.donnee = donnee;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
