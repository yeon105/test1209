package demo.react_springboot_test.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private String adminId;
    private String adminPw;
    private String adminName;
}
