package demo.react_springboot_test.controller;

import demo.react_springboot_test.data.dto.PostDTO;
import demo.react_springboot_test.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/save-post")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
        this.postService.savePost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

    @DeleteMapping(value = "/delete-post")
    public ResponseEntity<String> deletePost(@RequestParam Long id) {
        this.postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("해당 게시물이 삭제되었습니다.");
    }
}
