package demo.react_springboot_test.service;

import demo.react_springboot_test.data.dao.MemberDAO;
import demo.react_springboot_test.data.dto.MemberDTO;
import demo.react_springboot_test.data.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.builder()
                .id(memberDTO.getId())
                .pw(passwordEncoder.encode(memberDTO.getPw()))
                .name(memberDTO.getName())
                .address(memberDTO.getAddress())
                .regDate(LocalDateTime.now())
                .build();
        this.memberDAO.saveMember(memberEntity);
    }
}
