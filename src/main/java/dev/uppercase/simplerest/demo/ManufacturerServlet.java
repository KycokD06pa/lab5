package dev.uppercase.simplerest.demo;

import com.google.gson.Gson;
import dev.uppercase.simplerest.demo.dao.ManufacturerDao;
import dev.uppercase.simplerest.demo.entity.Manufacturer;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/manufacturers/*")
public class ManufacturerServlet extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            ManufacturerDao manufacturerDao = new ManufacturerDao();
            String json = this.toJson(manufacturerDao.getAll());
            this.outputResponse(resp, json, 200);
        } else {
            if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
                String numberString = pathInfo.replace("/", "");
                ManufacturerDao manufacturerDao = new ManufacturerDao();
                String json = this.toJson(manufacturerDao.get(numberString));
                if (json == null) {
                    this.outputResponse(resp, "Not found", 404);
                } else {
                    this.outputResponse(resp, json, 200);
                }
            }else {
                this.outputResponse(resp, "Incorrect link", 500);
            }
        }
    }
        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String pathInfo = req.getPathInfo();
            String name_of_manufacturer = req.getParameter("name");
            String country = req.getParameter("country");
            if (pathInfo == null || pathInfo.equals("/")) {
                if (name_of_manufacturer != null && country != null) {
                    ManufacturerDao manufacturerDao = new ManufacturerDao();
                    boolean isCreated = manufacturerDao.add(new Manufacturer(name_of_manufacturer, country));
                    if(isCreated){
                        this.outputResponse(resp, "add manufacturer", 200);
                    }else{
                        this.outputResponse(resp, "fall manufacturer", 405);
                    }
                }else {
                    this.outputResponse(resp, "incorrect link." +
                            "\n change link as manufacturers?name=Name&country=Country", 500);
                }

            }
        }
        @Override
        protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String pathInfo = req.getPathInfo();
            String name_of_manufacturer = req.getParameter("name");
            String country = req.getParameter("country");
            if (pathInfo == null || pathInfo.equals("/")) {
                if (name_of_manufacturer != null && country != null) {
                    ManufacturerDao manufacturerDao = new ManufacturerDao();
                    boolean isCreated = manufacturerDao.delete(new Manufacturer(name_of_manufacturer, country));
                    if(isCreated){
                        this.outputResponse(resp, "delete manufacturer", 200);
                    }else{
                        this.outputResponse(resp, "fall deleting", 405);
                    }
                }else {
                    this.outputResponse(resp, "incorrect link." +
                            "\n change link as manufacturers?name=Name&country=Country", 500);
                }

            }else {
                if (pathInfo.matches("\\/[0-9]+\\/{0,1}")) {
                    String numberString = pathInfo.replace("/", "");
                    ManufacturerDao manufacturerDao = new ManufacturerDao();
                    boolean isCreated = manufacturerDao.delete(numberString);
                    if(isCreated){
                        this.outputResponse(resp, "delete manufacturer", 200);
                    }else{
                        this.outputResponse(resp, "fall deleting", 405);
                    }
                }else {
                    this.outputResponse(resp, "incorrect link." +
                            "\n change link as manufacturers?name=Name&country=Country", 500);
                }
            }
        }

        @Override
        protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String pathInfo = req.getPathInfo();
            String name_of_manufacturer1 = req.getParameter("name1");
            String country1 = req.getParameter("country1");
            String name_of_manufacturer2 = req.getParameter("name2");
            String country2 = req.getParameter("country2");
            if (pathInfo == null || pathInfo.equals("/")) {
                if (name_of_manufacturer1 != null && country1 != null && name_of_manufacturer2 != null && country2 != null) {
                    ManufacturerDao manufacturerDao = new ManufacturerDao();
                    boolean isCreated = manufacturerDao.update(new Manufacturer(name_of_manufacturer1, country1), new Manufacturer(name_of_manufacturer2,country2));
                    if(isCreated){
                        this.outputResponse(resp, "update manufacturer", 200);
                    }else{
                        this.outputResponse(resp, "fall update", 405);
                    }
                }else {
                    this.outputResponse(resp, "incorrect link." +
                            "\n change link as manufacturers?name=Name&country=Country", 500);
                }

            }
        }


    private String toJson(Object list) {
        if (list == null) return null;
        Gson gson = new Gson();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }

    private void outputResponse(HttpServletResponse response, String payload, int status){

        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



