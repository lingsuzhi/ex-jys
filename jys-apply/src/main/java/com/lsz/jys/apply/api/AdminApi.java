package com.lsz.jys.apply.api;


import com.lsz.jys.apply.dto.admin.AdminHomeDTO;
import com.lsz.jys.apply.dto.admin.ImageCodeVO;
import com.lsz.jys.apply.dto.admin.LoginDTO;
import com.lsz.jys.apply.dto.admin.MiniAdminDTO;
import com.lsz.jys.common.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/admin")
public class AdminApi {




    @RequestMapping(value = "/login")
    public ResponseInfo<MiniAdminDTO> login(@RequestBody LoginDTO loginDTO) {
        MiniAdminDTO miniAdminDTO = new MiniAdminDTO();
        miniAdminDTO.setLoginTime(LocalDateTime.now());
        miniAdminDTO.setToken("admin@adsfafds");
        miniAdminDTO.setUserId("admin");
        return ResponseInfo.assertion(miniAdminDTO);
    }

    @RequestMapping(value = "/getCurrentUser")
    public ResponseInfo<AdminHomeDTO> getCurrentUser() {
        AdminHomeDTO adminHomeDTO = new AdminHomeDTO();
        adminHomeDTO.setAdmin(true);
        adminHomeDTO.setRole(0);
        adminHomeDTO.setName("管理员");
        adminHomeDTO.setUserId("admin");
        return ResponseInfo.assertion(adminHomeDTO);
    }
    @GetMapping("getImageCode/{id}")
    public ResponseInfo<ImageCodeVO> getImageCode(@PathVariable String id) {
        ImageCodeVO imageCodeVO = new ImageCodeVO();
        return ResponseInfo.assertion(imageCodeVO);
    }
}
