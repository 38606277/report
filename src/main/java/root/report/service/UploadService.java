package root.report.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Hex;
import root.report.db.DbFactory;
import root.report.util.FileUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2019/3/19.
 */
@Service
public class UploadService {

    private static Logger log = Logger.getLogger(UploadService.class);

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String upload(MultipartFile file) throws Exception {
        //基础路径  E:/springboot-upload/image/
        String basePath =System.getProperty("user.dir") + File.separator + "upload";
        File destFile = new File(basePath);
        destFile.getParentFile().mkdirs();
        //获取文件保存路径 \20180608\113339\
        String folder = FileUtils.getFolder();
        // 获取前缀为"FL_" 长度为20 的文件名  FL_eUljOejPseMeDg86h.png
        String fileName = FileUtils.getFileName() + FileUtils.getFileNameSub(file.getOriginalFilename());
        String filepath=null;
        try {
            // E:\springboot-upload\image\20180608\113339
            Path filePath = Files.createDirectories(Paths.get(basePath, folder));
            log.info(filePath);
            filepath=filePath.toString();
            //写入文件  E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Path fullPath = Paths.get(basePath, folder, fileName);
            log.info(fullPath);
            String fullPaths=fullPath.toString();
            int i=fullPaths.indexOf("upload");
            String usefilepath=fullPaths.substring(i,fullPaths.length());
            log.info(usefilepath);
            // E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Files.write(fullPath, file.getBytes(), StandardOpenOption.CREATE);
            //保存文件信息
            Map<String,Object> map=new HashMap<>();
            map.put("fileoriginname",file.getOriginalFilename());
            map.put("filename",fileName);
            map.put("filepath",fullPaths);
            map.put("filesize",file.getSize());
            map.put("filetype",file.getContentType());
            map.put("usefilepath",usefilepath);
            DbFactory.Open(DbFactory.FORM).insert("upload.addUpload",map);


        } catch (Exception e) {
            Path path = Paths.get(basePath, folder);
            log.error("写入文件异常,删除文件。。。。", e);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return e.getMessage();
        }
        return filepath;
    }


    /**
     * 文件下载
     *
     * @param fileName
     * @param res
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     */
//    public void downloadFile(String fileName, HttpServletResponse res) throws BusinessException, UnsupportedEncodingException {
//        if (fileName == null) {
//            throw new BusinessException("1001", "文件名不能为空");
//        }
//
//        // 通过文件名查找文件信息
//        FileInfo fileInfo = fileInfoDao.findByFileName(fileName);
//        log.info("fileInfo-->{}", fileInfo);
//        if (fileInfo == null) {
//            throw new BusinessException("2001", "文件名不存在");
//        }
//
//        //设置响应头
//        res.setContentType("application/force-download");// 设置强制下载不打开
//        res.addHeader("Content-Disposition", "attachment;fileName=" +
//                new String(fileInfo.getFileOriginName().getBytes("gbk"), "iso8859-1"));// 设置文件名
//        res.setHeader("Context-Type", "application/xmsdownload");
//
//        //判断文件是否存在
//        File file = new File(Paths.get(fileInfo.getFilePath(), fileName).toString());
//        if (file.exists()) {
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            try {
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                OutputStream os = res.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//                log.info("下载成功");
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new BusinessException("9999", e.getMessage());
//            } finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//    }
}
