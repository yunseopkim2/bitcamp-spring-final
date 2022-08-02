package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.UserDTO;
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

    void deleteArticle(Long articleId);

    List<Article> findAllQna(ArticleDTO articleDTO);

    List<Article> findComment(ArticleDTO articleDTO);

    List<Article> findByUsernameToArticle(String username);

    List<Article> findAll(Sort sort);

    List<Article> findByTitle(ArticleDTO articleDTO);

    Page<Article> findAll(Pageable pageable);

    List<Article> findMyQna(ArticleDTO articleDTO);

    long count();

    void delete(ArticleDTO articleDTO) throws Exception;

    Messenger save(ArticleDTO article);

    void saveQna(ArticleDTO article) throws Exception;

    Optional<Article> findById(ArticleDTO articleDTO);

    boolean existsById(String article);

    void partialUpdate(ArticleDTO articleDTO) throws Exception;

    List<Article> findByToken(UserDTO userDTO);

    List<Article> findByUsername(String username);

}
