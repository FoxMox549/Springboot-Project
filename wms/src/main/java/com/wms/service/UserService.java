package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  Service class
 * </p>
 *
 * @author wms
 * @since 2024-04-14
 */
public interface UserService extends IService<User> {

    IPage pageC(IPage<User> page);

    IPage pageCC(Page<User> page, Wrapper wrapper);
}
