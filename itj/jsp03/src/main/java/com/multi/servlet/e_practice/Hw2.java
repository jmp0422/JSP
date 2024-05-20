package com.multi.servlet.e_practice;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet("/monday2.do")
public class Hw2 extends HttpServlet {
    String temp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("객체 생성 됨");
        temp = config.getInitParameter("temp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get요청받음");
        PrintWriter out = response.getWriter();
        out.print("get~~parameter values >> " + temp);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Do POST request handling here
    }
}