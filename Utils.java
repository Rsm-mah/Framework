package utils;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Utils {
    
    public String getUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String url = request.getServletPath();
        out.println(url);

        return url;
    }
}
