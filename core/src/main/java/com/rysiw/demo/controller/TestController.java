package com.rysiw.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
import com.rysiw.demo.common.utils.ResultUtil;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.dao.UserTestMapper;
import com.rysiw.demo.exception.DefineException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.exception.TikaException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * controller for test
 *
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private final static OkHttpClient okHttpClient = new OkHttpClient();

    @RequestMapping("/client")
    public String tt() throws IOException {
        String URL = "http://gateway/client/test";
        Request request = new Request
                .Builder()
                .url(URL)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("########Response Code:########");
        System.out.println(response.body());
        return response.body().string();
    }

    @RequestMapping("/exception")
    public String exception() {
        throw new DefineException("2","22");
    }

    @GetMapping("/queryRequest")
    public ResultVO<Object> queryRequest(@RequestParam(required = true) String userId){

        System.out.println(DateTime.now());
        return ResultVO.builder().build();
    }

    @GetMapping("/unzipTest")
    public void unzipTest() throws IOException, TikaException {
        File uploadFile = new File("/Users/rysiw/Downloads/202208101111.zip");

//        File unzipFile = ZipUtil.unzip(uploadFile, new File("/Users/rysiw/Desktop/cis/newrequirement/tmpdata/uncompress"), CharsetUtil.CHARSET_GBK);
        File unzipFile = ZipUtil.unzip(uploadFile, new File("/Users/rysiw/Desktop/cis/newrequirement/tmpdata/uncompress"));
        if(unzipFile.isDirectory()){
            String[] children = unzipFile.list();
            boolean targetExist = false;
            String targetFileName = "";
            if(children != null) {
                String regex = "^\\d{4}\\d{1,2}\\d{1,2}";
                for (String fileN : children) {
                    if(fileN.matches(regex)){
                        targetExist = true;
                        targetFileName = fileN;
                        break;
                    }
                }
                if(!targetExist){
                    System.out.println("error");
                }
                //校验解压文件夹中secret文件是否存在
                File targetFile = new File("/Users/rysiw/Desktop/cis/newrequirement/tmpdata/uncompress/" + targetFileName + File.separator);
                String secretFileName = targetFileName + "_secret.txt";
                if(targetFile.isDirectory()){
                    String[] targetChildren = targetFile.list();
                    if(targetChildren != null) {
                        if(Arrays.asList(targetChildren).contains(secretFileName)){
                            cn.hutool.core.io.FileUtil.move(targetFile, new File("/Users/rysiw/Desktop/cis/newrequirement/tmpdata/connection" + File.separator), true);
                        }else{
                            System.out.println("error");
                        }
                    }
                }else{
                    System.out.println("error");
                }
            }
        }
        return ;
    }

    public File unzip(File zipFile, String descDir) {
        try (ZipArchiveInputStream inputStream = new ZipArchiveInputStream(new BufferedInputStream(Files.newInputStream(zipFile.toPath())));) {
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipArchiveEntry entry = null;
            while ((entry = inputStream.getNextZipEntry()) != null) {
                if (entry.isDirectory()) {
                    File directory = new File(descDir, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(new File(descDir, entry.getName())));
                        IOUtils.copy(inputStream, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (Exception e) {
//            log.error("[unzip] 解压zip文件出错", e);
//            throw new BusinessException(ErrorsEnum.FILE_MANAGE_FILE_UPLOAD_ERROR.getErrorCode(), "[unzip] 解压zip文件出错");
        }
        return new File(descDir);
    }
}
