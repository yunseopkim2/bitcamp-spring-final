package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.common.blank.StringUtils;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final UserRepository userRepository;


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
                    .open(article.isOpen())
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
   public Messenger delete(ArticleDTO articleDTO) {
       Article article = new Article();
       userRepository.findByUsername(articleDTO.getUser().getUsername()).equals(article.getUser().getUsername());
       return Messenger.builder().message("삭제 완료").build();
   }


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
@Transactional
public ArticleDTO getPost(Long articleId) {
    Article board = repository.findById(articleId).get();

    ArticleDTO boardDto = ArticleDTO.builder()
            .articleId(board.getArticleId())
            .title(board.getTitle())
            .content(board.getContent())
            .fileId(board.getFileId())
            .writtenDate(board.getWrittenDate())
            .build();
    return boardDto;
}
    @Override @Transactional
    public void partialUpdate(final ArticleDTO articleDTO) {
        Optional<Article> originArticle = repository.findById(articleDTO.getArticleId());

        Article article = originArticle.get();
        if(StringUtils.isNotBlank(articleDTO.getTitle())) article.setTitle(articleDTO.getTitle());
        if(StringUtils.isNotBlank(articleDTO.getWrittenDate())) article.setWrittenDate(articleDTO.getWrittenDate());
        if(StringUtils.isNotBlank(articleDTO.getContent())) article.setContent(articleDTO.getContent());
        if(StringUtils.isNotBlank(articleDTO.getPicture())) article.setPicture(articleDTO.getPicture());
        if(StringUtils.isNotBlank(articleDTO.getHeight())) article.setHeight(articleDTO.getHeight());
        if(StringUtils.isNotBlank(articleDTO.getWeight())) article.setWeight(articleDTO.getWeight());
        if(StringUtils.isNotBlank(articleDTO.getComment())) article.setComment(articleDTO.getComment());
        repository.save(article);
    }

    @Override
    public Article findByTokenQna(ArticleDTO articleDTO) {
    User user = userRepository.findByToken(articleDTO.getUser().getToken()).orElse(null);
    user.getArticles();


        return null;
    }
}
