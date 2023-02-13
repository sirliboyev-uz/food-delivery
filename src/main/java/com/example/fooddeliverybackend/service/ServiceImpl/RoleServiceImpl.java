package com.example.fooddeliverybackend.service.ServiceImpl;

import com.example.fooddeliverybackend.dto.ApiResponse;
import com.example.fooddeliverybackend.dto.RoleRegisterDto;
import com.example.fooddeliverybackend.entity.Role;
import com.example.fooddeliverybackend.repository.RoleRepository;
import com.example.fooddeliverybackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public ApiResponse register(RoleRegisterDto roleRegisterDTO) {
        if(roleRepository.existsByRoleName(roleRegisterDTO.getName())) return new ApiResponse("Role already registered",false);
        Role role=new Role(
                roleRegisterDTO.getName(),
                roleRegisterDTO.getPermissionList());
        roleRepository.save(role);
        return new ApiResponse("Role Successfully registered!",true);
    }
    @Override
    public ApiResponse update(Long id, RoleRegisterDto dto) {
        Optional<Role> optionalRole=roleRepository.findById(id);
        if(optionalRole.isPresent()){
            if(roleRepository.existsByRoleNameAndIdNot(dto.getName(),id))
                return new ApiResponse("Role already registered",false);
            Role role=optionalRole.get();
            role.setRoleName(dto.getName());
            role.setPermissions(dto.getPermissionList());
            roleRepository.save(role);
            return new ApiResponse("Successfully updated",true);
        }
        return new ApiResponse("Role not found",false);
    }
    @Override
    public ApiResponse delete(Long id) {
        Optional<Role> optionalRole=roleRepository.findById(id);
        if(optionalRole.isPresent()){
            roleRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Role not found",false);
    }
    @Override
    public ApiResponse role(Long id) {
        Optional<Role> role=roleRepository.findById(id);
        if(role.isPresent()){
            return new ApiResponse("Success",true, role.get());
        }
        return new ApiResponse("Not found",false);
    }
    @Override
    public ApiResponse roles() {
        List<Role> roles=roleRepository.findAll();
        return new ApiResponse("Success",true,roles);
    }
}
