package kr.co.clozet.articles.controllers;

import io.swagger.annotations.Api;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags ="articles")
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;
    private final ArticleRepository repository;

    @PostMapping(value = "/write")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDTO article) {
        System.out.println("게시글 정보: " + article.toString());
        return ResponseEntity.ok(service.save(article));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Article>> findAllArticles() {
        return ResponseEntity.ok(repository.findAllArticle());
    }

    @DeleteMapping(value = "/delete") @ResponseBody
    public void delete1(@RequestBody ArticleDTO articleId){
        service.deleteArticle(articleId.getArticleId());
    }

    @PatchMapping(value = "/update") @ResponseBody
    public void partialUpdate(@RequestBody final ArticleDTO articleDTO) throws Exception{
        service.partialUpdate(articleDTO);
    }

    @PostMapping("/myList") @ResponseBody
    public ResponseEntity<List<Article>> findByTokenToArticle(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(repository.findByTokenToArticle(articleDTO.getToken()));
    }

    @PostMapping(value = "/write/qna") @ResponseBody
    public void saveQna(@RequestBody ArticleDTO article) throws Exception{
        System.out.println("QnA 정보: " + article.toString());
        service.saveQna(article);
    }

    @PostMapping(value = "/list/qna")
    public ResponseEntity<List<Article>> findByQnaDateASC(@RequestBody ArticleDTO article) {
        return ResponseEntity.ok(repository.findByQnaDateASC(article.getOpen()));
    }

    @PostMapping(value = "/myList/qna") @ResponseBody
    public ResponseEntity<List<Article>> findMyQna(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(repository.findMyQna(articleDTO.getToken()));
    }

    @PostMapping(value = "/list/comment") @ResponseBody
    public ResponseEntity<List<Article>> findComment(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(repository.findByTitle(articleDTO.getTitle()));
    }

    @DeleteMapping(value = "/delete/comment") @ResponseBody
    public void deleteComment(@RequestBody ArticleDTO articleDTO) {
        repository.deleteComment(articleDTO.getToken(), articleDTO.getArticleId());
    }
    @GetMapping(value = "/count") @ResponseBody
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }
}