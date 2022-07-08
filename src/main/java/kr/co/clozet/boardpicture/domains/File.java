package kr.co.clozet.boardpicture.domains;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * packageName    : kr.co.clozet.boardpicture.domains
 * fileName       : BoardPicture
 * author         : kimyunseop
 * date           : 2022-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-08        kimyunseop       최초 생성
 */
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;
}
