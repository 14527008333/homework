package com.zhk.zhkopencart.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhk.zhkopencart.dao.AdministratorMapper;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import com.zhk.zhkopencart.po.Administrator;
import com.zhk.zhkopencart.service.AdministratorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class AdministratorServerImp implements AdministratorServer {


    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator selectByUsername(String userName) {
        Administrator administrator= administratorMapper.selectByUsername(userName);
        return administrator;
    }

    @Override
    public Administrator getAdministratorById(Integer administratorId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(administratorId);
        return administrator;
    }

    @Override
    public Page<AdministratorListDTO> getUserList(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        Page<AdministratorListDTO> administratorListDTOS= administratorMapper.getUserList();
        return administratorListDTOS;
    }

    @Override
    public Integer create(Administrator administrator) {
        administratorMapper.insertSelective(administrator);
        return administrator.getAdministratorId();
    }

    @Override
    public void update(Administrator administrator) {
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }
}
