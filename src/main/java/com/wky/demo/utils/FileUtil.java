package com.wky.demo.utils;

import com.ruigu.rbox.cloud.kanai.web.exception.GlobalRuntimeException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author User
 * @create 2021-07
 * @description:
 */
public class FileUtil {

    /**
     * 网络url转为文件
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static File getFile(String url, String fileName) throws Exception {
        File file = null;
        URL fileUrl;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile(fileName + "_", ".jpg");
            //下载
            fileUrl = new URL(url);
            inStream = fileUrl.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 功能:压缩多个文件，输出压缩后的zip文件流
     *
     * @throws Exception
     */
    public static void exportZip(List<File> files) throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new GlobalRuntimeException(500, "获取参数失败");
        }
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        assert response != null;
        String zipFileName = "发票";
        byte[] buf = new byte[1024];
        // 获取输出流
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        zipFileName = URLEncoder.encode(zipFileName + ".zip", "UTF-8");
        ZipOutputStream out = null;
        try {
            response.reset(); // 重点突出
            // 不同类型的文件对应不同的MIME类型
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + zipFileName);

            // ZipOutputStream类：完成文件或文件夹的压缩
            assert bos != null;
            out = new ZipOutputStream(bos);
            for (File file : files) {
                FileInputStream in = new FileInputStream(file);
                // 给列表中的文件单独命名
                out.putNextEntry(new ZipEntry(
                        file.getName().substring(0, file.getName().lastIndexOf("_"))
                                + file.getName().substring(file.getName().lastIndexOf("."))));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(out)) {
                out.flush();
                out.close();
            }
            if (Objects.nonNull(bos)) {
                bos.close();
            }
        }
    }

}