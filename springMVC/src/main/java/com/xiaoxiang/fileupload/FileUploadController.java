package com.xiaoxiang.fileupload;

import com.xiaoxiang.result.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String configPath = null;
    private String uploadPath = File.separator + "fileupload" + File.separator;
    private String uploadPathTemp = File.separator + "fileupload" + File.separator;
    private String tempPath = "";

    /**
     * 初始化相关设置 设置上传路径
     */
    public void init(HttpServletRequest request) {
        configPath = request.getSession().getServletContext().getRealPath("") + System.getProperty("file.separator");
        uploadPath = configPath + uploadPath;
        tempPath = uploadPath + "tmp" + File.separator;
        File fileUpload = new File(uploadPath);
        File fileUploadTemp = new File(tempPath);
        if (!fileUpload.exists()) {
            if (!fileUpload.mkdir()) {
                System.out.println("无法创建存储目录!");
                return;
            }
        }
        if (!fileUploadTemp.exists()) {
            if (!fileUploadTemp.mkdir()) {
                System.out.println("无法创建临时存储目录!");
            }
        }
    }

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fileupload/upload.vm");
        return modelAndView;
    }

    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
    }

    @RequestMapping(method = RequestMethod.GET)
    public void fileUploadForm() {
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResult processUpload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONResult result = new JSONResult();
        try {
            init(request);
            response.setContentType(CONTENT_TYPE);           
            request.setCharacterEncoding("UTF-8");
            CommonsMultipartFile files = (CommonsMultipartFile)file;
            File fileTemp = new File(uploadPath +file.getName());
            FileCopyUtils.copy(file.getBytes(),fileTemp);
            //
            result.setResult(true);
            result.setData("File '" + file.getOriginalFilename() + "' uploaded successfully");
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }

}

class AjaxUtils {

    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter("ajaxUpload") != null;
    }

    private AjaxUtils() {
    }

}
