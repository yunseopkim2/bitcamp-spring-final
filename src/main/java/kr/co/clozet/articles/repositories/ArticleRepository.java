package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName:kr.co.clozet.board.repositories
 * fileName        :ArticleRepository.java
 * author          : kimyunseop
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           kimyunseop      최초 생성
 **/

interface ArticleCustomRepository{
    // 000. title 과 content 를 수정하시오
    void update(Article article);
    @Modifying
    @Query("DELETE FROM Article WHERE Article.user.userId =: userId")
    void deleteByUserId(@Param("userId") Long userId);
}


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByTitleContaining(String keyword);
}
