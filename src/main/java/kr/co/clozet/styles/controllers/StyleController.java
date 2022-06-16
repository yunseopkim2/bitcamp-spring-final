package kr.co.clozet.styles.controllers;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.events.domains.Event;
import kr.co.clozet.styles.domains.Style;
import kr.co.clozet.styles.services.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.clothes.controllers
 * fileName        :StyleController.java
 * author          : kimyunseop
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           kimyunseop      최초 생성
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/styles")
public class StyleController {
    private final StyleService service;
    @GetMapping("/findAll")
    public ResponseEntity<List<Style>> findAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<Style>> findAll(Sort sort) {return ResponseEntity.ok(service.findAll(sort));}

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<Style>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));}

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody Style style) {
        return ResponseEntity.ok(service.delete(style));}


    @GetMapping("/findById/{userId}")
    public ResponseEntity<Optional<Style>> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.findByUserId(userId));}


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Messenger> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));}

    @PutMapping("/update")
    public ResponseEntity<Messenger> update(@RequestBody Style style) {
        return ResponseEntity.ok(service.update(style));
    }
}
