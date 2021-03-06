package com.zhk.zhkopencart.dao;

import com.github.pagehelper.Page;
import com.zhk.zhkopencart.dto.out.AdministratorListDTO;
import com.zhk.zhkopencart.dto.out.AdministratorShowDTO;
import com.zhk.zhkopencart.po.Administrator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    Administrator selectByUsername(@Param("userName") String userName);

    AdministratorShowDTO getAdministratorById(@Param("administratorId")Integer administratorId);

    Page<AdministratorListDTO> getUserList();

    Administrator getAdministratorByEmail(String email);
}