package demo.react_springboot_test.data.dao;

import demo.react_springboot_test.data.entity.PostEntity;
import demo.react_springboot_test.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostDAO {

    private final PostRepository postRepository;

    public void savePost(PostEntity postEntity) {
        this.postRepository.save(postEntity);
    }

    public void deletePost(Long postId) {
        this.postRepository.deleteById(postId);
    }

}
