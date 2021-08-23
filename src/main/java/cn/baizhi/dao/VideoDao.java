package cn.baizhi.dao;

import cn.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查
    List<Video> queryByPage(@Param("start") int start, @Param("end") int end);

    //添加
    void add(Video video);

    //查总条数
    int queryCount();
}
