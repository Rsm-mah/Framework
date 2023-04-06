package model;

import etu1877.framework.Url_annotation;
import modelview.ModelView;


public class Emp {
    @Url_annotation(url = "/getAll")
    public ModelView getAll(){
        ModelView modelview = new ModelView();
        modelview.setUrl("Emp.jsp");

        return modelview;
    }
}
