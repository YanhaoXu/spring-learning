package com.github.xuyh.app.domain.convertor;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.xuyh.app.domain.entity.Role;
import com.github.xuyh.app.domain.vo.RoleVO;

@Mapper
public interface RoleConvertor {

  RoleConvertor INSTANCE = Mappers.getMapper(RoleConvertor.class);

  RoleVO toRoleVO(Role role);

  List<RoleVO> toRoleVOList(List<Role> roles);

}
