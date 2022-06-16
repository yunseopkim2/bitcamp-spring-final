package kr.co.clozet.colors.controllers;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.services.ClothesService;
import kr.co.clozet.colors.domains.Color;
import kr.co.clozet.colors.services.ColorService;
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
 * fileName        :ColorController.java
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
@RequestMapping("/colors")
public class ColorController {

    private final ColorService service;

    @GetMapping("/findAll")
    public ResponseEntity<List<Color>> findAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<Color>> findAll(Sort sort) {return ResponseEntity.ok(service.findAll(sort));}

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<Color>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));}

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}



    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody Color color) {
        return ResponseEntity.ok(service.delete(color));}


    @GetMapping("/findById/{userId}")
    public ResponseEntity<Optional<Color>> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.findByUserId(userId));}


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Messenger> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));}

    @PutMapping("/update")
    public ResponseEntity<Messenger> update(@RequestBody Color color) {
        return ResponseEntity.ok(service.update(color));
    }

}
