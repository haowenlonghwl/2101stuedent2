package cn.baizhi.service;


import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import cn.baizhi.util.AliYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoDao vd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {
        Map<String, Object> map = new HashMap<>();
        int start = (page - 1) * size;
        //需要在页面上显示数据
        List<Video> videos = vd.queryByPage(start, size);

        map.put("data", videos);
        //页面显示当前页
        map.put("page", page);
        //总条数
        int i = vd.queryCount();
        //页面也需要显示总页数
        int pageNum = i % size == 0 ? i / size : i / size + 1;
        map.put("pageNum", pageNum);
        return map;


    }

    @Override
    public void add(MultipartFile file, Video video) {

        long time = new Date().getTime();
        String fileName =time+file.getOriginalFilename();
        AliYun.uploadByBytes(file, "video/"+fileName);









    }

}
