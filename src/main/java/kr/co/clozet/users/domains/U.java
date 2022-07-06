package kr.co.clozet.users.domains;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : kr.co.clozet.users.domains
 * fileName       : U
 * author         : kimyunseop
 * date           : 2022-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06        kimyunseop       최초 생성
 */
@ToString
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class U {
    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;


}
