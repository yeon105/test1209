package demo.react_springboot_test.data.repository;

import demo.react_springboot_test.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
}
