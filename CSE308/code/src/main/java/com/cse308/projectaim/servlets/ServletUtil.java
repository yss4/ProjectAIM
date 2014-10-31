package com.cse308.projectaim.servlets;

import com.cse308.projectaim.beans.AIMEntityListBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import com.cse308.projectaim.hibernate.types.AIMFileWrapper;
import com.cse308.projectaim.services.AIMService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

public class ServletUtil {

    private Logger logger = Logger.getLogger(getClass());

    public String getAll(HttpSession hs, AIMService service, AIMEntity entity, String listBeanName)
            throws ServletException, IOException {
        AIMEntityListBean listBean = (AIMEntityListBean) hs.getAttribute(listBeanName);
        if (null == listBean) {
            listBean = new AIMEntityListBean();
            hs.setAttribute(listBeanName, listBean);
        }
        List<AIMEntity> list = service.getAll(entity);
        String returnPage = "./list.jsp";
        if (null == list) {
            returnPage = "/common/requestFailure.jsp";
            logger.error("[ServletUtil] Problem about retrieving list for " + entity.getClass().getName());
        } else {
            listBean.setList(list);
            hs.setAttribute(listBeanName, listBean);
            logger.info("[ServletUtil] Retrieving list for " + entity.getClass().getName());
        }
        return returnPage;
    }
    
    public byte[] readFully(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    public String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        //logger.info("Part Header = " + partHeader);
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
    
    public AIMFileWrapper buildFileWrapper(Part part) throws IOException {
		AIMFileWrapper fileWrapper = new AIMFileWrapper();
		if (part.getSize() != 0) {
			fileWrapper.setFileName(getFileName(part));
			byte[] bytes = readFully(part.getInputStream());
			fileWrapper.setBytes(bytes);
		}
		return fileWrapper;
	}
}
