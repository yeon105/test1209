package demo.react_springboot_test.data.repository;

import demo.react_springboot_test.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
