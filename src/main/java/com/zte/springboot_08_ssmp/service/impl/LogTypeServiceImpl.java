package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.LogType;
import com.zte.springboot_08_ssmp.mapper.LogTypeMapper;
import com.zte.springboot_08_ssmp.service.LogTypeService;
import org.springframework.stereotype.Service;

@Service
public class LogTypeServiceImpl extends ServiceImpl<LogTypeMapper, LogType> implements LogTypeService {
}
