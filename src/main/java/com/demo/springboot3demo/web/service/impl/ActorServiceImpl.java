package com.demo.springboot3demo.web.service.impl;

import com.demo.springboot3demo.web.entity.Actor;
import com.demo.springboot3demo.web.mapper.ActorMapper;
import com.demo.springboot3demo.web.service.ActorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 演员表 服务实现类
 * </p>
 *
 * @author Lives@gamevector
 * @since 2024-06-10
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements ActorService {

}
