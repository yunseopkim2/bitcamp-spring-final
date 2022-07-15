package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.users.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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


    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT a.height FROM Article a WHERE a.title LIKE '%안녕%'")
    String[] searchByTitleLike(
    );
    @Transactional
    @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.title = :title")
    int updateView(@Param("title") String title);

    @Query(value = "SELECT a FROM Article a where a.user.token = :token")
    Article findByTokenToArticle(@Param("token") String token);

    @Transactional @Modifying
    @Query("delete from Article a where a.user.token in :token and a.title = :title")
    void deleteArticle(@Param("token") String token, @Param("title") String title);


}

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository{
    List<Article> findByTitleContaining(String title);
    Page<Article> findByTitleContaining(String keyword, Pageable pageable);
    Optional<Article> findByUserUserId(long userId);
    Optional<Article> findByOpen(String open);
    List<Article> deleteArticleByArticleId(long articleId);

    void delete(Article article);
    //Optional<Article> findByUsername(String uesrname);
    //void deleteByArticle(ArticleDTO article);
}
