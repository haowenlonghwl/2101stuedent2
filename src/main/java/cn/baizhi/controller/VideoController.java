package cn.baizhi.controller;


import cn.baizhi.config.AliYunConfig;
import cn.baizhi.entity.Category;
import cn.baizhi.entity.Video;
import cn.baizhi.service.VideoService;
import cn.baizhi.util.AliYun;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService vs;


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("queryByPage")
    public Map<String, Object> queryByPage(int page){
        int size = 2;
        return vs.queryByPage(page, size);

    }


    @RequestMapping("/add")
    public void add(MultipartFile video, String title, String brief, String id){
        log.debug("执行了");
        log.debug("标题:" + title);
        log.debug("描述:" + brief);
        log.debug("二级类别的id:" + id);
        System.out.println(video.getOriginalFilename());
        Video video1 = new Video(null,title,brief,null,null,null,new Category(id, null, null, null),null,null);
        vs.add(video, video1);

        //视频上传阿里云 OSS
       /* String filename = new Date().getTime() +video.getOriginalFilename();
        AliYun.uploadByBytes(video, "/video"+filename);


        String endpoint = AliYunConfig.ENDPOINT;
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;

        String bucketName = "2101student";
        String objectName = "/video"+filename;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String style = "video/snapshot,t_50000,f_jpg,w_800.h_600";
        Date expiration = new Date(new Date().getTime() + 1000 * 60 *10);
        GeneratePresignedUrlRequest req  = new GeneratePresignedUrlRequest(bucketName, objectName);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);

        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl.toString()).openStream();
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] split = filename.split("\\.");
        ossClient.putObject("2101student", "video/"+split[0]+".jpg",inputStream);
        ossClient.shutdown();*/
    }
}
