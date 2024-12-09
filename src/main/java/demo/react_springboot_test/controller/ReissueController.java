package demo.react_springboot_test.controller;

import demo.react_springboot_test.jwt.Jwtutil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReissueController {

    private final Jwtutil jwtutil;

    @PostMapping(value = "/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest req, HttpServletResponse res) {
        String refresh = null;
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
                break;
            }
        }

        if (refresh == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("refresh token null");
        }

        try {
            this.jwtutil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("refresh token 유효기간 만료");
        }

        String category = this.jwtutil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 refresh 토큰");
        }

        String username = this.jwtutil.getUsername(refresh);
        String role = this.jwtutil.getRole(refresh);

        String newAccessToken = this.jwtutil.CreateJWT("access", username, role, 5000L);
        res.addHeader("Authorization", "Bearer " + newAccessToken);
        return ResponseEntity.status(HttpStatus.OK).body("새 토큰 발급 성공");
    }
}
