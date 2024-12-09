package demo.react_springboot_test.controller;

import demo.react_springboot_test.data.dto.AdminDTO;
import demo.react_springboot_test.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save-admin")
    public ResponseEntity<String> saveAdmin(@RequestBody AdminDTO admin) {
        this.adminService.saveAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }
}
