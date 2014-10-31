package com.cse308.projectaim.servlets;

import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.services.AIMService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class FileServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(getClass());
	private AIMService fileService = new AIMService();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		logger.info("Controller get a request downloading a File.");

		String fileId = request.getParameter("fileId");

		AIMFileWrapper fileWrapper = new AIMFileWrapper();
		fileWrapper.setId(Integer.valueOf(fileId));
		fileWrapper = (AIMFileWrapper) fileService.retrieve(fileWrapper);


		response.reset();
		response.setContentType("application/text");
		response.setHeader("Content-disposition", "attachment; filename=" + fileWrapper.getFileName());

		byte[] buf = new byte[7128];
		ByteArrayInputStream bis = new ByteArrayInputStream(fileWrapper.getBytes());
		int len;
		while ((len = bis.read(buf)) > 0) {
			response.getOutputStream().write(buf, 0, len);
		}
		bis.close();
		response.getOutputStream().flush();
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
		return "FileServlet";
	}
}
