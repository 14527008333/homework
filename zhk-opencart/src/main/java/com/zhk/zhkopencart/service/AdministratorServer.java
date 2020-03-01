package com.zhk.zhkopencart.service;

import com.zhk.zhkopencart.po.Administrator;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorServer {

    Administrator selectByUsername(String userName);
}
