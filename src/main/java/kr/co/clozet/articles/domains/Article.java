package kr.co.clozet.articles.domains;

import com.sun.istack.NotNull;
import kr.co.clozet.boards.domains.Board;
import kr.co.clozet.users.domains.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * packageName:kr.co.clozet.auth.domains
 * fileName        :Article.java
 * author          : kimyunseop
 * date            :2022-05-18
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-18           kimyunseop      최초 생성
 **/
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name="articles")
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue private long articleId;
    @Column private String title;
    @CreatedDate
    @Column(name = "written_date", updatable = false)  private String writtenDate;
    @Column private String inquiry;
    @Column private String open;
    @Column private String content;
    @Column private String picture;
    @Column private String height;
    @Column private String weight;
    @Column private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;






}
