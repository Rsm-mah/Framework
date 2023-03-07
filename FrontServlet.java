package etu1877.framework.servlet;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;

import etu1877.framework.Mapping;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping>  MappingUrls;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = request.getServletPath();
        out.println(url);
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
