package kr.co.clozet.articles.controllers;

import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.articles.domains.ArticleDTO;
import kr.co.clozet.articles.repositories.ArticleRepository;
import kr.co.clozet.articles.services.ArticleService;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.boardpicture.domains.FileDTO;
import kr.co.clozet.boardpicture.services.FileService;
import kr.co.clozet.boardpicture.util.MD5Generator;
import kr.co.clozet.users.domains.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private final ModelMapper modelMapper;
    private FileService fileService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Article>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findByTokenToArticle") @ResponseBody
    public ResponseEntity<Article> findByTokenToArticle(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.findByTokenToArticle(userDTO.getToken()));
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
 @PostMapping("/post")
 public String write(@RequestParam("files") MultipartFile files, ArticleDTO boardDto) {
     try {
         String origFilename = files.getOriginalFilename();
         String filename = new MD5Generator(origFilename).toString();
         /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
         String savePath = System.getProperty("user.dir") + "\\files";
         /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
         if (!new File(savePath).exists()) {
             try{
                 new File(savePath).mkdir();
             }
             catch(Exception e){
                 e.getStackTrace();
             }
         }
         String filePath = savePath + "\\" + filename;
         files.transferTo(new File(filePath));

         FileDTO fileDto = new FileDTO();
         fileDto.setOrigFilename(origFilename);
         fileDto.setFilename(filename);
         fileDto.setFilePath(filePath);

         Long fileId = fileService.saveFile(fileDto);
         boardDto.setFileId(fileId);
         Article article = modelMapper.map(boardDto, Article.class);
         service.save(article);
     } catch(Exception e) {
         e.printStackTrace();
     }
     return "성공";
 }
    @GetMapping("/posts/{title}") @ResponseBody
    public Integer read(@PathVariable("title") String title) {
         // views ++
        ResponseEntity.ok(repository.updateView(title));
        Article article = new Article();
        return article.getView();
    }
    @PatchMapping(value = "/update") @ResponseBody
    public void partialUpdate(@RequestBody final ArticleDTO articleDTO) {
      service.partialUpdate(articleDTO);
    }
}
