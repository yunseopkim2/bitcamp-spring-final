package kr.co.clozet.articles.repositories;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT a.height FROM Article a WHERE a.title LIKE '%안녕%'")
    String[] searchByTitleLike(
    );
    @Modifying
    @Query("update Article a set a.view = a.view + 1 where a.user.userId = :userId")
    int updateView(Long id);
}

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findByTitleContaining(String title);
    Page<Article> findByTitleContaining(String keyword, Pageable pageable);

    //void deleteByArticle(ArticleDTO article);
}
