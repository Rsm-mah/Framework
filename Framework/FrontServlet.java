package etu1877.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import etu1877.framework.Mapping;
import etu1877.framework.Url_annotation;
import etu1877.framework.Utils;
import modelview.ModelView;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping>  MappingUrls;

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

            //out.println("url : " + utils.getUrl(request)+"<br>");

            // for (Map.Entry<String,Mapping> infoEntry : MappingUrls.entrySet()) {
            //     out.println(infoEntry.getKey()+ " ,dans la class : " + infoEntry.getValue().getClassName() + " ,method : " + infoEntry.getValue().getMethod() +"\n");
            // }

            Class cls = Class.forName(mapping.getClassName());
            Object object = cls.getConstructor().newInstance();
            ModelView modelview = new ModelView();
            
            //SPRINT 7
            Field[] attribut = cls.getDeclaredFields();
            for (int i = 0; i < attribut.length; i++) {
                if (request.getParameter(attribut[i].getName())!=null) {
                    Field f = cls.getDeclaredField(attribut[i].getName());
                    f.setAccessible(true);
                    String value = request.getParameter(attribut[i].getName());
                    f.set(object, utils.conversion(value,f.getType()));
                }
            }
            Method[] met = cls.getDeclaredMethods();
            for (int i = 0; i < met.length; i++) {
                if (met[i].getName().equals(mapping.getMethod())) {
                    modelview = (ModelView)met[i].invoke(object);
                }
            }

            HashMap<String,Object> data = modelview.getDonnee();

            for (Map.Entry infoEntry : data.entrySet()) {
                request.setAttribute((String)infoEntry.getKey(),infoEntry.getValue());
                System.out.println( infoEntry.getValue().getClass());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(modelview.getUrl());
            dispatcher.forward(request,response);

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
