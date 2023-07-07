package model;

import etu1877.framework.Url_annotation;
import etu1877.framework.Utils;
import modelview.ModelView;

import java.io.InputStream;

import javax.servlet.http.*;

public class FileUpload {
    String name;
    String path;
    byte[] bytesize;

    public FileUpload() {

    }

    public FileUpload(String name, String path, byte[] bytesize) {
        this.setName(name);
        this.setPath(path);
        this.setBytesize(bytesize);
    }

    @Url_annotation(url = "/file")
    public ModelView AfficheFichier() {
        ModelView modelView = new ModelView();
        modelView.setUrl("AffichageFichier.jsp");

        return modelView;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytesize() {
        return bytesize;
    }

    public void setBytesize(byte[] bytesize) {
        this.bytesize = bytesize;
    }


}
