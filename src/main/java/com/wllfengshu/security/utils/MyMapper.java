package com.wllfengshu.security.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 *     注意：该接口不能被扫描到，否则会出错
 *
 * @author
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
