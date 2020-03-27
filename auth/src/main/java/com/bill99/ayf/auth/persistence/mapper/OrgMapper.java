package com.bill99.ayf.auth.persistence.mapper;

import com.bill99.ayf.auth.persistence.entity.Org;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}