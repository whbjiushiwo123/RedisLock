package com.whb.dao;

import com.whb.model.GoodsInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    List<GoodsInfoEntity> getGoodsInfo(@Param("id") String id);
}