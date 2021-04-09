package com.mybatis.test.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {

    int insertSelective(Demo record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Demo record);

    @Select("SELECT * FROM demo WHERE id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    }
    )
    Demo queryByPrimaryKey(Long id);

    Demo selectByPrimaryKey(Long id);
}
