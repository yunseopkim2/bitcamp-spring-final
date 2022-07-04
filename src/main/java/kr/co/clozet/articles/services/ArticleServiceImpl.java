package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.board.services
 * fileName        :ArticleServiceImpl.java
 * author          : kimyunseop
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           kimyunseop      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;


    @Override
    public List<Article> findAll() {
        return repository.findAll();
    }
    @Override
    public Messenger update(Article article) {
        final Optional<Article> original = repository.findById(article.getUser().getUserId());
        original.ifPresent(article1 -> {
            Article.builder().height(article.getHeight())
                    .weight(article.getWeight())
                    .picture(article.getPicture())
                    .content(article.getContent())
                    .open(article.getOpen())
                    .inquiry(article.getInquiry())
                    .title(article.getTitle())
                    .comment(article.getComment())
                    .build();
            repository.save(article1);
        });
        return Messenger.builder().message("업데이트 완료").build();
    }
    @Override
    public List<Article> findAll(Sort sort) {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "writtenDate"));
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        Pageable sortedByArticles =
                PageRequest.of(0, 5, Sort.by("writtenDate")
                        .descending()
                        .and(Sort.by("writtenDate")));
        return repository.findAll(sortedByArticles);
    }

    @Override
    public Messenger count() {

        repository.count();
        return Messenger.builder().message("").build() ;
    }

   /* @Override
    public Messenger delete(ArticleDTO article) {
        Article article1 = new Article();
        if(article.getArticleId() == article1.getArticleId()){
            repository.deleteByArticle(article);

        }
        return Messenger.builder().message("삭제되었습니다.").build();
    }
*/


    @Override
    public Messenger save(Article article) {
        repository.save(article);
        return Messenger.builder().message("등록되었습니다.").build();
    }

    @Override
    public Optional<Article> findById(Long userId) {
        return repository.findById(0L);
    }

    @Override
    public Messenger existsById(String article) {

        repository.existsById(0L);
        return Messenger.builder().message("중복된 결과입니다.").build();
    }

    @Override
    public Messenger removeComment(Long userId) {
        return null;
    }

    @Override
    public List<Article> search(String title) {
         List<Article> searchByTitle = repository.findByTitleContaining(title);
        return searchByTitle;
    }

   /* @Override
    public Integer updateView(Long userId) {
        Article article = new Article();
        int a = repository.updateView(userId);
        article.setView(a);
        repository.save(article);
        return repository.updateView(userId);
    }*/

/*
    @Override
    public Page<Article> search(String keyword, Pageable pageable) {
        Page<Article> postsList = repository.findByTitleContaining(keyword, pageable);
        return postsList;
    }
*/

}
