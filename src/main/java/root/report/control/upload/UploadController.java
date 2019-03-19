package root.report.control.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import root.report.common.RO;
import root.report.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Administrator on 2019/3/19.
 */
@RestController
@RequestMapping("/reportServer/uploadFile")
public class UploadController  extends RO {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/uploadFile", produces = "text/html;charset=UTF-8")
    public String uplaod(HttpServletRequest req, @RequestParam("file") MultipartFile file) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
            // 判断文件是否为空
            if (file.isEmpty()) {
                return ExceptionMsg("文件不能为空");
            }
            String pathname=null;
            try {
                pathname= uploadService.upload(file);
            } catch (Exception e) {
                ExceptionMsg(e.getMessage());
            }
            return SuccessMsg("",pathname);
    }
}