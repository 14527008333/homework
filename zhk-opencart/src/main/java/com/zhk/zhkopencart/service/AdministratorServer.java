package com.zhk.zhkopencart.service;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import com.zhk.zhkopencart.po.Administrator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorServer {

    Administrator selectByUsername(String userName);

    Administrator getAdministratorById(Integer administratorId);

    Page<AdministratorListDTO> getUserList(Integer pageNum);
}
