package com.citco.banking.ccs.web.action.web.fileUpload;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.citco.banking.ccs.util.common.CCSLoggerFactory;
import com.citco.banking.ccs.util.common.SystemWideConstants;
import com.citco.banking.ccs.util.domain.order.MessageOrderUnprocessed;
import com.citco.banking.ccs.util.domain.order.MessageOrderUnprocessedFilter;
import com.citco.banking.ccs.util.service.MessageService;
import com.citco.banking.ccs.util.service.order.OrderService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

/**
 * Created by uy438 on 14/5/2021.
 */
public class FileUploadAction extends HttpServlet {

    //set location where file to be stored while testing
    // correct path = "/jccsbin/jccs/data/messagetransformationengine
    // filePath = "C:\\testUpload\\";

    //declare variables
    private final String UPLOAD_DIRECTORY = "C:\\testUpload\\";

    @Action("file-upload")
    public String execute() {
        return SUCCESS;
    }

    //doPost called when form is submitted.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check for file upload request
        //isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/HTML");
        PrintWriter out = response.getWriter();

        //try parse file or catch exception
        try {
            //create fileItem and iterator
            List<FileItem> fields  = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            Iterator<FileItem> it = fields.iterator();
            //loop through file and write to UPLOAD_DIRECTORY
            for (FileItem item : fields){
                String name = new File(item.getName()).getName();
                item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
            }
            out.println("<p> File uploaded </p>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
    }
}

/*
        //check if file upload request
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                //loop through input
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        //get name and write file
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
                //file upload successful
                request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        } else {
            request.setAttribute("message", "Sorry this Servlet only handles file upload request");
        }

        request.getRequestDispatcher("..\\..\\..\\..\\..\\..\\..\\..\\..\\webapp\\WEB-INF\\content\\web\\fileupload\\result.jsp").forward(request, response);
    }
    */