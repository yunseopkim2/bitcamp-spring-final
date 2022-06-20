package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public List<Article> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Messenger delete(Article article) {

        repository.delete(article);
        return Messenger.builder().message("삭제되었습니다.").build();
    }



    @Override
    public Messenger save(Article article) {
        repository.save(article);
        return Messenger.builder().message("등록되었습니다.").build();
    }

    @Override
    public Optional<Article> findById(String article) {
        return repository.findById(0L);
    }

    @Override
    public boolean existsById(String article) {
        return repository.existsById(0L);
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
}
