package com.zhk.zhkopencart.service.imp;

import com.zhk.zhkopencart.dao.AdministratorMapper;
import com.zhk.zhkopencart.po.Administrator;
import com.zhk.zhkopencart.service.AdministratorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AdministratorServerImp implements AdministratorServer {


    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator selectByUsername(String userName) {
        Administrator administrator= administratorMapper.selectByUsername(userName);
        return administrator;
    }
}
