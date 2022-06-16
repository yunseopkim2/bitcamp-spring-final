package kr.co.clozet.articles.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * packageName:kr.co.clozet.articles.domains
 * fileName        :ArticleDTO.java
 * author          : kimyunseop
 * date            :2022-06-15
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-15           kimyunseop      최초 생성
 **/
@Component @Data
public class ArticleDTO {
    @ApiModelProperty(position = 1) private long articleId;
    @ApiModelProperty(position = 2) String title;
    @ApiModelProperty(position = 3) private String writtenDate;
    @ApiModelProperty(position = 4) String inquiry;
    @ApiModelProperty(position = 5) String open;
    @ApiModelProperty(position = 6) String content;
    @ApiModelProperty(position = 7) String picture;
    @ApiModelProperty(position = 8) String height;
    @ApiModelProperty(position = 9) String weight;
    @ApiModelProperty(position = 10) String userId;
    @ApiModelProperty(position = 11) String boardId;
}
