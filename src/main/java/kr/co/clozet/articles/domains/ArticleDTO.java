package kr.co.clozet.articles.domains;

import io.swagger.annotations.ApiModelProperty;
import kr.co.clozet.boards.domains.Board;
import kr.co.clozet.users.domains.User;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

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
@Component @Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @ApiModelProperty(position = 10) long userId;
    @ApiModelProperty(position = 11) Board board;
    @ApiModelProperty(position = 12) int view;
    @ApiModelProperty(position = 13) private Long fileId;
    @ApiModelProperty(position = 14) private String comment;
    @ApiModelProperty(position = 15 ) User user;
    @ApiModelProperty(position = 16 ) String token;

    public Article toEntity(){
        Article articles = Article.builder()
                .articleId(articleId)
                .title(title)
                .writtenDate(writtenDate)
                .inquiry(inquiry)
                .open(open)
                .content(content)
                .picture(picture)
                .height(height)
                .weight(weight)
                .user(user)
                .fileId(fileId)
                .board(board).build();
        return null;
    }

    public void addAttribute(String searchList, Page<Article> searchList1) {
    }
}
