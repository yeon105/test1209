package demo.react_springboot_test.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @Column(name = "member_id", nullable = false, length = 8)
    private String id;
    @Column(name = "member_pw", nullable = false)
    private String pw;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(name = "regdate")
    private LocalDateTime regDate;
}
