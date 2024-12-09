package demo.react_springboot_test.data.dao;

import demo.react_springboot_test.data.entity.AdminEntity;
import demo.react_springboot_test.data.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminDAO {

    private final AdminRepository adminRepository;

    public void saveAdmin(AdminEntity adminEntity) {
        this.adminRepository.save(adminEntity);
    }
}
