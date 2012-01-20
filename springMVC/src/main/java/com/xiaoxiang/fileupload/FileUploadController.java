package com.xiaoxiang.fileupload;

import com.xiaoxiang.result.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
    protected static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    /**
     * 初始化相关设置 设置上传路径
     */
    public String init(HttpServletRequest request) {
        String configPath = null;
        String uploadPath = File.separator + "fileupload" + File.separator;
        String uploadPathTemp = File.separator + "fileupload" + File.separator;
        String tempPath = "";
        configPath = request.getSession().getServletContext().getRealPath("") + System.getProperty("file.separator");
        uploadPath = configPath + uploadPath;
        tempPath = uploadPath + "tmp" + File.separator;
        File fileUpload = new File(uploadPath);
        File fileUploadTemp = new File(tempPath);
        if (!fileUpload.exists()) {
            if (!fileUpload.mkdir()) {
                System.out.println("无法创建存储目录!");
                return null;
            }
        }
        if (!fileUploadTemp.exists()) {
            if (!fileUploadTemp.mkdir()) {
                System.out.println("无法创建临时存储目录!");
            }
        }
        return uploadPath;
    }

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fileupload/upload.vm");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void fileUploadForm() {
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResult processUpload(@RequestParam String param,MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONResult result = new JSONResult();
        try {
            System.out.println(param);
            String uploadPath = init(request);
            response.setContentType(CONTENT_TYPE);           
            request.setCharacterEncoding("UTF-8");
            //取所有name等于file,且type='file'的元素
            List<MultipartFile> files = request.getFiles("file");
            for(MultipartFile file:files){
                File fileTemp = new File(uploadPath +((CommonsMultipartFile) file).getFileItem().getName());
                FileCopyUtils.copy(file.getBytes(),fileTemp);
                logger.info("File '" + file.getOriginalFilename() + "' uploaded successfully");
            }
            //
            result.setResult(true);
            result.setData(null);
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }

}
