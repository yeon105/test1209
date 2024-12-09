package demo.react_springboot_test.data.repository;

import demo.react_springboot_test.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
