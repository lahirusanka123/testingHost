/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.code_complexity.contraller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Lahiru Sanka
 */
@WebServlet(name = "main_servlet", urlPatterns = {"/main_servlet"})
public class main_servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession httpsession = request.getSession();

        try {

            String path = main_servlet.class.getClassLoader().getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            String pathArr[] = fullPath.split("Fileupload/"); //get WEB INF Path
            String pdfname = "";

            System.out.println("*****ado****" + pathArr[0]);

            if (ServletFileUpload.isMultipartContent(request)) {

                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<Object> items = upload.parseRequest(request);
                for (Object x : items) {
                    FileItem fileitem = (FileItem) x;

                    System.out.println("" + fileitem);

                    // String extention = (fileitem.getName().contains(".") ? fileitem.getName().substring(fileitem.getName().lastIndexOf('.')) : "");
                    // String name = System.currentTimeMillis() + "_" + fileitem.getName().hashCode() + extention;
                    if (fileitem.getSize() < 0) {
                        
                        out.print("Error: No File Found");
                        
                    } else {

                        System.out.println("Directory is Exist!" + fileitem);
                        // String extention = (fileitem.getName().contains(".") ? fileitem.getName().substring(fileitem.getName().lastIndexOf('.')) : "");

                        File image = new File(pathArr[0] + "/Files/" + fileitem.getName()); //save file in server
                        if (image.exists()) {

                            out.print("Error: File Name Already Exist!");
                        } else {

                            image.createNewFile();
                            FileOutputStream fos = new FileOutputStream(image);
                            fos.write(fileitem.get());
                            fos.close();
                            out.print("Uploaded");
                            
                             

                            try (BufferedReader br = new BufferedReader(new FileReader(path + "Files/" + fileitem.getName()))) {
                                StringBuilder sb = new StringBuilder();
                                String line = br.readLine();

                                while (line != null) {
                                    sb.append(line);
                                    sb.append(System.lineSeparator());
                                    line = br.readLine();
                                }
                                
                                String everything = sb.toString();

//  Calculate contral structure

                                Calculate_contral_structure ccs = new Calculate_contral_structure();
                                String Ctrlstruct_calculatedvalue = ccs.calculate(everything);
                                
                                //  Calculate Coupling 


                                                // add your one here


                                //  Calculate  Size, variables, and methods 


                                                // add your one here


                                //  Calculate Inheritance

                                                // add your one here                 

//                                out.print("this one>>>>>" + everything);
                                 
                                httpsession.setAttribute("Readfile", Ctrlstruct_calculatedvalue);
//                                System.out.println("**************session*********" + httpsession.getAttribute("Readfile"));
                                 
                            }

                        }

                    }

                }

            } else if (request.getParameter("RqData") != null) {
                 
                out.print(httpsession.getAttribute("Readfile"));

            } else {
                out.print("Error: No File Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
