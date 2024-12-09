package demo.react_springboot_test.service;

import demo.react_springboot_test.data.entity.AdminEntity;
import demo.react_springboot_test.data.entity.MemberEntity;
import demo.react_springboot_test.data.repository.AdminRepository;
import demo.react_springboot_test.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // username : PK

        Optional<AdminEntity> adminEntity = this.adminRepository.findById(username);
        if (adminEntity.isPresent() && adminEntity.get().getAdminId().startsWith("admin")) {
            AdminEntity admin = adminEntity.get();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getAdminId(), admin.getAdminPw(), grantedAuthorities);
        }

        Optional<MemberEntity> memberEntity = this.memberRepository.findById(username);
        if (memberEntity.isEmpty()) {
            throw new UsernameNotFoundException(username + "님은 회원이 아닙니다. 회원가입을 해주세요.");
        }

        MemberEntity member = memberEntity.get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(member.getId(), member.getPw(), grantedAuthorities);
    }
}
