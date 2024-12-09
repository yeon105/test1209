package demo.react_springboot_test.service;

import demo.react_springboot_test.data.dao.AdminDAO;
import demo.react_springboot_test.data.dto.AdminDTO;
import demo.react_springboot_test.data.entity.AdminEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminDAO adminDAO;
    private final PasswordEncoder passwordEncoder;

    public void saveAdmin(AdminDTO adminDTO) {
        AdminEntity adminEntity = AdminEntity.builder()
                .adminId(adminDTO.getAdminId())
                .adminPw(passwordEncoder.encode(adminDTO.getAdminPw()))
                .adminName(adminDTO.getAdminName())
                .build();
        this.adminDAO.saveAdmin(adminEntity);
    }
}
