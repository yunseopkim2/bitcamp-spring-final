package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import org.springframework.data.domain.Sort;
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

    @Query(value = "SELECT a FROM Article a where a.user.username = :username")
    List<Article> findByUsernameToArticle(@Param("username") String username);

    @Query(value = "SELECT a FROM Article a where a.token = :token and a.open is null")
    List<Article> findByTokenToArticle(@Param("token") String token);

    @Query(value = "SELECT a FROM Article a WHERE a.open is null")
    List<Article> findAllArticle();

    @Transactional @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.title = :title")
    int updateView(@Param("title") String title);

    @Transactional @Modifying
    @Query("delete from Article a where a.user.token in :token and a.title = :title")
    void deleteArticle(@Param("token") String token, @Param("title") String title);

    @Query("select a.articleId FROM Article a join User u on u.userId = a.user.userId where a.user.username = :username")
    List<Article> findByUsername(@Param("username") String username);

    @Query("SELECT a from Article a WHERE a.open LIKE 'true' order by a.writtenDate ASC")
    List<Article> findByQnaDateASC(@Param("open") String open);


    @Query(value = "SELECT a FROM Article a where a.token = :token and a.open is not null")
    List<Article> findMyQna(@Param("token") String token);

    @Transactional @Modifying
    @Query("delete from Article a where a.token in :token and a.articleId = :articleId")
    void deleteComment(@Param("token") String token, @Param("articleId") long articleId);
}


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    List<Article> findByTitle(String title);
    List<Article> findByOpen(String open);
    List<Article> findByUserUserId(long userId);
    List<Article> findAll(Sort sort);
    //Optional<Article> findByToken(String token);
    List<Article> findByToken(String token);
    void deleteArticleByArticleId(Long articleId);

}
