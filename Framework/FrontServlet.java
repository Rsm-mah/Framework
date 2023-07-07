package etu1877.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import etu1877.framework.Mapping;
import etu1877.framework.Url_annotation;
import etu1877.framework.Utils;
import modelview.ModelView;
import model.FileUpload;

@MultipartConfig
public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping>  MappingUrls;
    HashMap<String,Object>  MappingSingleton = new HashMap<String, Object>();

    @Override
    public void init() throws ServletException {
        super.init();
        try {

            Utils utils = new Utils();
            this.MappingUrls = new HashMap<>();
            List<Class<?>> allClass = utils.getClasse_in_Package("model");
            Mapping mapping;
            Method[] Methods;
            for(Class<?> c : allClass) {
                Methods = c.getMethods();

                for(Method m : Methods) {
                    if(m.isAnnotationPresent(Url_annotation.class)) {
                        mapping = new Mapping();
                        mapping.setClassName(c.getName());
                        mapping.setMethod(m.getName());

                        MappingUrls.put(m.getAnnotation(Url_annotation.class).url(), mapping);

                    }
                    //SPRINT 10
                    else if (c.isAnnotationPresent(Scope.class)) {
                        mapping = new Mapping();
                        mapping.setClassName(c.getName());
                        mapping.setMethod("Scope"); 

                        MappingUrls.put(c.getAnnotation(Scope.class).type(), mapping); 
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
    
            Utils utils = new Utils();
            Mapping mapping = MappingUrls.get(utils.getUrl(request));
    
            if (mapping == null) {
                throw new Exception("Tsisy");
            }
    
            // Récupère la classe correspondante au mapping
            Class<?> cls = Class.forName(mapping.getClassName());

            //SPRINT 10
            boolean singleton = false;
            if (cls.isAnnotationPresent(Scope.class)) {
                if (cls.getAnnotation(Scope.class).type().equals("singleton")) {
                    singleton = true;
                    System.out.println(cls.getName() + " est de type singleton");
                }
            }

            // Instancie la classe (en tant que singleton si applicable)
            Object object;
            if (singleton) {
                // Vérifie si l'instance singleton existe déjà
                if (MappingSingleton.containsKey(cls)) {
                    object = MappingSingleton.get(cls);
                } else {
                    object = cls.getConstructor().newInstance();
                    MappingSingleton.put(cls.getName(), object);
                }
            } else {
                object = cls.getConstructor().newInstance();
            }
    
            // Instancie la classe
            //Object object = cls.getConstructor().newInstance();
    
            ModelView modelview = new ModelView();
            //HashMap<String, Object> data = modelview.getDonnee();
    

            //SPRINT 7
            // Récupère les attributs de la classe
            Field[] attribut = cls.getDeclaredFields();
            for (int i = 0; i < attribut.length; i++) {
    
                // Vérifie si un paramètre correspondant à l'attribut est présent dans la requête
                if (request.getParameter(attribut[i].getName()) != null) {
                    Field f = cls.getDeclaredField(attribut[i].getName());
                    f.setAccessible(true);
    
                    // Récupère la valeur du paramètre de la requête
                    String value = request.getParameter(attribut[i].getName());
    
                    // Effectue une conversion de la valeur et l'assigne à l'attribut
                    f.set(object, utils.conversion(value, f.getType()));
                }
            }
    
            //SPRINT 8
            // Récupère les méthodes de la classe
            Method[] met = cls.getDeclaredMethods();
            for (int i = 0; i < met.length; i++) {
    
                // Vérifie si une méthode correspondante au mapping est trouvée
                if (met[i].getName().equals(mapping.getMethod())) {
    
                    if (met[i].getParameterCount() > 0) {
                        Class<?>[] parametersTypes = met[i].getParameterTypes();
    
                        Object[] arguments = new Object[parametersTypes.length];
    
                        // Parcourt les paramètres
                        for (int j = 0; j < parametersTypes.length; j++) {
    
                            // Vérifie si le paramètre est de type String
                            if (parametersTypes[j].equals(String.class)) {
                                // Obtient le nom du paramètre
                                String paramName = met[i].getParameters()[j].getName();
    
                                // Obtient la valeur du paramètre depuis la requête
                                String paramValue = request.getParameter(paramName);
    
                                // Ajoute la valeur du paramètre à l'array d'arguments
                                arguments[j] = paramValue;
                            }
                        }

                        // Appelle la méthode sur l'objet
                        modelview = (ModelView) met[i].invoke(object, arguments);
                    } 
                    else {
                        // Appelle la méthode sans arguments
                        modelview = (ModelView) met[i].invoke(object);
                    }
                }                

            }

            //SPRINT 9
            String contentType = request.getContentType();
            if (contentType != null && contentType.startsWith("multipart/form-data")) {
                for (Part p : request.getParts()) {
                    try {
                        String name_fichier = utils.getFileName(p);
                        byte[] bytes = utils.getFileBytes(p);   

                        FileUpload fileupload = new FileUpload(name_fichier,null,bytes);
                        modelview.addItem("fichier",fileupload);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }
            
    
            // Ajoute les données à la requête
            for (Map.Entry<String, Object> infoEntry : modelview.getDonnee().entrySet()) {
                request.setAttribute(infoEntry.getKey(), infoEntry.getValue());
            }
    
            // Transfère la requête au dispatcher pour afficher la vue correspondante
            RequestDispatcher dispatcher = request.getRequestDispatcher(modelview.getUrl());
            dispatcher.forward(request, response);
    
        } catch (Exception e) {
            e.printStackTrace(out);
            // TODO: handle exception
        }
    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
                
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
                
    }

}
