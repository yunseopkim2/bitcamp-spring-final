package kr.co.clozet.boardpicture.domains;

import lombok.*;

/**
 * packageName    : kr.co.clozet.boardpicture.domains
 * fileName       : FileDTO
 * author         : kimyunseop
 * date           : 2022-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-08        kimyunseop       최초 생성
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;


    public File toEntity() {
        File build = File.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

}
