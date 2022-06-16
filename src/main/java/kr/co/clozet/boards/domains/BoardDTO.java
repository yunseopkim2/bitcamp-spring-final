package kr.co.clozet.boards.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * packageName:kr.co.clozet.boards.domains
 * fileName        :BoardDTO.java
 * author          : kimyunseop
 * date            :2022-06-15
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-15           kimyunseop      최초 생성
 **/
@Component @Data
public class BoardDTO {
    @ApiModelProperty(position = 1) private long boardId;
    @ApiModelProperty(position = 8) private String createdDate;
    @ApiModelProperty(position = 12) private String qna;
}
