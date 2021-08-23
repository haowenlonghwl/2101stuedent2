package cn.baizhi.service;

import cn.baizhi.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VideoService {
    //分页业务
    Map<String,Object> queryByPage(int page, int size);

    //添加视频业务
    void add(MultipartFile file, Video video);

}
