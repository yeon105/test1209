package demo.react_springboot_test.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @Column(name = "admin_id", nullable = false, length = 8)
    private String adminId;
    @Column(name = "admin_pw", nullable = false)
    private String adminPw;
    @Column(name = "admin_name", nullable = false)
    private String adminName;
}
