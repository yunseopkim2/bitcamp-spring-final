package kr.co.clozet.articles.services;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.common.blank.StringUtils;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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
    public List<Article> findAllQna(ArticleDTO articleDTO) {
        List<Article> article = repository.findByOpen(String.valueOf(Objects.equals(articleDTO.getOpen(), "true")));
        // article = repository.findAll(Sort.by(Sort.Direction.DESC, "writtenDate"));
        return article;
    }

    @Override
    public List<Article> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Article findByTitle(ArticleDTO articleDTO) {
        Article article =repository.findByTitle(articleDTO.getTitle()).orElse(null);
        return article;
    }

    @Override
    public Page findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Article> findMyQna(ArticleDTO articleDTO) {
        List<Article> article = new ArrayList<>();
        if(articleDTO.getOpen() != null){
            article = repository.findByToken(articleDTO.getToken());
        }
        return article;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override @Transactional
    public void delete(ArticleDTO articleDTO) {
        /*Article article = new Article();
        article.setToken(articleDTO.getToken());
        repository.save(article);
        List<Article> article1 = repository.findByToken(article.getToken());*/
        repository.deleteArticleByArticleId(articleDTO.getArticleId());
    }

    @Override
    public List<Article> findByUsernameToArticle(String username) {
        return repository.findByUsernameToArticle(username);
    }

    @Override
    public Messenger save(ArticleDTO article) {
        System.out.println("서비스로 전달된 게시글 정보: "+article.toString());
        String result = "";
        if (repository.findByTitle(article.getTitle()).isEmpty()) {
            repository.save(Article.builder()
                    .title(article.getTitle())
                    .writtenDate(article.getWrittenDate())
                    .content(article.getContent())
                    .height(article.getHeight())
                    .weight(article.getWeight())
                    .comment(article.getComment())
                    .token(article.getToken())
                    .nickname(article.getNickname())
                    .comment(article.getComment())
                    .build());
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
        return Messenger.builder().message(result).build();
    }

    @Override
    public void saveQna(ArticleDTO article) {
        System.out.println("서비스로 전달된 QnA 정보: "+article.toString());
        String result = "";
        if (repository.findByTitle(article.getTitle()).isEmpty()) {
            repository.save(Article.builder()
                    .title(article.getTitle())
                    .content(article.getContent())
                    .open(article.getOpen())
                    .nickname(article.getNickname())
                    .token(article.getToken())
                    .writtenDate(article.getWrittenDate())
                    .build());
            result = "SUCCESS";
        } else {
            result = "FAIL";
        }
    }

    @Override
    public Optional<Article> findById(ArticleDTO articleDTO) {
        return repository.findById(articleDTO.getArticleId());
    }

    @Override
    public boolean existsById(String article) {
        return repository.existsById(0L);
    }

    @Override @Transactional
    public void partialUpdate(final ArticleDTO articleDTO) throws Exception{
        Optional<Article> originArticle = repository.findById(articleDTO.getArticleId());

        Article article = originArticle.get();
        if(StringUtils.isNotBlank(articleDTO.getTitle())) article.setTitle(articleDTO.getTitle());
        if(StringUtils.isNotBlank(articleDTO.getWrittenDate())) article.setWrittenDate(articleDTO.getWrittenDate());
        if(StringUtils.isNotBlank(articleDTO.getContent())) article.setContent(articleDTO.getContent());
        if(StringUtils.isNotBlank(articleDTO.getHeight())) article.setHeight(articleDTO.getHeight());
        if(StringUtils.isNotBlank(articleDTO.getWeight())) article.setWeight(articleDTO.getWeight());
        if(StringUtils.isNotBlank(articleDTO.getComment())) article.setComment(articleDTO.getComment());
        repository.save(article);
    }

    @Override
    public List<Article> findByToken(UserDTO userDTO) {
        User user = userRepository.findByToken(userDTO.getToken()).orElse(null);
        return user.getArticles();
    }



    @Override
    public List<Article> findByUsername(String username) {
        return repository.findByUsername(username);
    }

}