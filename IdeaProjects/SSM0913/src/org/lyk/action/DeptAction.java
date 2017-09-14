package org.lyk.action;

import org.lyk.vo.Dept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by liuyuank on 9/14/2017.
 */
@Controller
@RequestMapping("/pages/dept/*")
public class DeptAction
{
    private static final Logger LOGGER = LoggerFactory.getLogger("logfile");
    @Resource(name = "messageReader")
    private MessageSource messageReader;

    @RequestMapping("upload")
    public ModelAndView upload(Dept dept, MultipartFile photo)
    {
        try
        {

            ModelAndView mav = new ModelAndView("/page/dept/show.jsp");
            mav.addObject("dept", dept);
            if (!photo.isEmpty())
            {
                mav.addObject("fileName", photo.getOriginalFilename());
                mav.addObject("fileSize", photo.getSize());
                mav.addObject("fileType", photo.getContentType());

                if(this.saveFile(photo))
                {
                    LOGGER.info("成功保存上传文件");
                }
            }
            return mav;
        }
        catch (Exception e)
        {
            LOGGER.error("系统错误");
            LOGGER.error(e.getMessage(),e);
            return null;
        }
    }

    public boolean saveFile(MultipartFile multipartFile)
    {
        //todo
        String path = this.getFilePath(multipartFile);
        if(path == null)
        {
            return false;
        }
        File file = new File(path);
        if(!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        try
        {
            InputStream is = multipartFile.getInputStream();
            OutputStream os = new FileOutputStream(path);
            Integer len = -1;
            byte[] buffer = new byte[1024 * 1024];//1M
            while ((len = is.read(buffer)) != -1)
            {
                os.write(buffer, 0, len);
            }
            os.close();
            is.close();
            return true;
        } catch (IOException e)
        {
            LOGGER.error("保存文件失败");
            LOGGER.error(e.getMessage());
            return false;
        }

    }
    private String getFilePath(MultipartFile file)
    {
        String filePath = this.messageReader.getMessage("UPLOAD_FILE_SAVE_PATH", null, Locale.getDefault());
        String fileName = UUID.randomUUID().toString();
        if(file.getContentType().contains("jpeg"))
        {
            fileName += ".jpg";
        }
        else if(file.getContentType().contains("png"))
        {
            fileName += ".png";
        }
        else
        {
            LOGGER.info("不允许的文件类型");
            return null;
        }
        if(!filePath.endsWith("/"))
            filePath += "/";
        String fullPath = filePath + fileName;
        return fullPath;
    }
}
