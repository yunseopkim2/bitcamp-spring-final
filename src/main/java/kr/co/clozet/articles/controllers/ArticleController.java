package kr.co.clozet.articles.controllers;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * packageName:kr.co.clozet.board.controllers
 * fileName        :ArticleController.java
 * author          : kimyunseop
 * date            :2022-05-09
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-09           kimyunseop      최초 생성
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService service;
    private final ArticleRepository repository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<Article>> findAll(Sort sort) {
        return ResponseEntity.ok(service.findAll(sort));
    }

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<Article>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }
    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}

   @DeleteMapping("/delete")
    public  ResponseEntity<Messenger> delete(@RequestBody ArticleDTO article) {
        return ResponseEntity.ok(service.delete(article));
    }

    @PostMapping("/save")
    public  ResponseEntity<Messenger> save(@RequestBody Article article) {
        return ResponseEntity.ok(service.save(article));
    }

    @GetMapping("/findById/{userId}")
    public ResponseEntity<Optional<Article>> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @GetMapping("/existsById/{article}")
    public ResponseEntity<Messenger> existsById(@PathVariable String article) {
        return
                ResponseEntity.ok(service.existsById(article));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Article>> search(@PathVariable String title){
        return ResponseEntity.ok(service.search(title));}
/*
    @GetMapping("/search")
    public ResponseEntity<String[]> searchByTitleLike(){
        return ResponseEntity.ok(repository.searchByTitleLike());}*/

   /* @GetMapping("/posts/read/{userId}")public String read(@PathVariable Long userId, ArticleDTO articleDTO) {
        Optional<Article> articleDTO1= service.findById(userId);
        service.updateView(userId); // views ++
        return "posts-read";    }*/

 /*   @GetMapping("/posts/search") public String search(String keyword, ArticleDTO article, @PageableDefault(sort = "articleId", direction = Sort.Direction.DESC) Pageable pageable)
    {        Page<Article> searchList = service.search(keyword, pageable);
        article.addAttribute("searchList", searchList);
        return "posts-search";    }
*/
}
