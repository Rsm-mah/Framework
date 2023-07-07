package etu1877.framework;

import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyEditorSupport;
import java.beans.PropertyEditorManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.servlet.http.*;

public class Utils {

    public String getUrl(HttpServletRequest request) {
        String url = request.getServletPath();

        return url;
    }

    public List<Class<?>> getClasse_in_Package(String name_package) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = name_package.replace('.', '/');
        for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
            for (String file : new File(resource.toURI()).list()) {
                if (file.endsWith(".class")) {
                    Class<?> className = Class.forName(name_package + '.' + file.substring(0, file.length() - 6));
                    classes.add(className);
                }
            }
        }
        return classes;
    }

    public static <T> T conversion(String value, Class<T> type){
        PropertyEditorSupport editor = (PropertyEditorSupport) PropertyEditorManager.findEditor(type);
        editor.setAsText(value);
        return (T) editor.getValue();
    }

    public String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
    
    public byte[] getFileBytes(Part part) throws Exception {
        InputStream inputStream = part.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }
}
