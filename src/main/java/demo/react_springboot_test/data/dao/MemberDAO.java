package demo.react_springboot_test.data.dao;

import demo.react_springboot_test.data.entity.MemberEntity;
import demo.react_springboot_test.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDAO {

    private final MemberRepository memberRepository;

    public void saveMember(MemberEntity memberEntity) {
        memberRepository.save(memberEntity);
    }
}
