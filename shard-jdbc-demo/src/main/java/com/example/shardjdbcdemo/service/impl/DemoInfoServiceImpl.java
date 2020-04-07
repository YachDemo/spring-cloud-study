package com.example.shardjdbcdemo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardjdbcdemo.model.DemoInfo;
import com.example.shardjdbcdemo.mapper.DemoInfoMapper;
import com.example.shardjdbcdemo.service.DemoInfoService;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-03-31 15:28
 **/
@Service
public class DemoInfoServiceImpl extends ServiceImpl<DemoInfoMapper, DemoInfo> implements DemoInfoService {

}

