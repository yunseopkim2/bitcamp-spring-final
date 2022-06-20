package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.auth.domains.Messenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.board.services
 * fileName        :ArticleService.java
 * author          : kimyunseop
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           kimyunseop      최초 생성
 **/
public interface ArticleService {
    List<Article> findAll();

    List<Article> findAll(Sort sort);

    Page<Article> findAll(Pageable pageable);

    long count();

    Messenger delete(Article article);

    Messenger save(Article article);

    Optional<Article> findById(String article);

    boolean existsById(String article);

    Messenger removeComment(Long userId);

    List<Article> search(String title);
}
