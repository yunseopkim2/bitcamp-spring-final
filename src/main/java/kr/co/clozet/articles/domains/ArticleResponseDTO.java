package kr.co.clozet.articles.domains;

import io.swagger.annotations.ApiModelProperty;
import kr.co.clozet.boards.domains.Board;
import kr.co.clozet.users.domains.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : kr.co.clozet.articles.domains
 * fileName       : ArticleResponseDTO
 * author         : kimyunseop
 * date           : 2022-06-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21        kimyunseop       최초 생성
 */
@RequiredArgsConstructor
@Getter
public class ArticleResponseDTO {
    @ApiModelProperty(position = 1) private long articleId;
    @ApiModelProperty(position = 2) String title;
    @ApiModelProperty(position = 3) private String writtenDate;
    @ApiModelProperty(position = 4) String inquiry;
    @ApiModelProperty(position = 5) String open;
    @ApiModelProperty(position = 6) String content;
    @ApiModelProperty(position = 7) String picture;
    @ApiModelProperty(position = 8) String height;
    @ApiModelProperty(position = 9) String weight;
    @ApiModelProperty(position = 10)
    String nickname;
    @ApiModelProperty(position = 11)
    Board board;

    public ArticleResponseDTO(Article article){
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.writtenDate = article.getWrittenDate();
        this.inquiry = article.getInquiry();
        this.open = article.getOpen();
        this.content = article.getContent();
        this.picture = article.getPicture();
        this.height = article.getHeight();
        this.weight = article.getWeight();
        this.nickname = article.getUser().getNickname();
        this.board = article.getBoard();
    }
}
