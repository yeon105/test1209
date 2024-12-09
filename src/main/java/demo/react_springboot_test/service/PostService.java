package demo.react_springboot_test.service;

import demo.react_springboot_test.data.dao.PostDAO;
import demo.react_springboot_test.data.dto.PostDTO;
import demo.react_springboot_test.data.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostDAO postDAO;

    public void savePost(PostDTO postDTO) {
        PostEntity postEntity = PostEntity.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .author(postDTO.getAuthor())
                .created(LocalDateTime.now())
                .build();
        this.postDAO.savePost(postEntity);
    }

    public void deletePost(Long postId) {
        this.postDAO.deletePost(postId);
    }
}
