package kr.co.clozet.boards.controllers;

import kr.co.clozet.boards.domains.Board;
import kr.co.clozet.boards.domains.BoardDTO;
import kr.co.clozet.boards.services.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.controllers
 * fileName        :BoardController.java
 * author          : kimyunseop
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           kimyunseop      최초 생성
 **/
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/findAll")
    public List<Board> findAll() {
        return service.findAll();
    }

    @GetMapping("/findAll/sort")
    public List<Board> findAll(Sort sort) {
        return service.findAll(sort);
    }

    @GetMapping("/findAll/pageable")
    public Page<Board> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody Board board) {
        return service.delete(board);
    }

    @PostMapping("/save")
    public String save(@RequestBody Board board) {
        return service.save(board);
    }

    @GetMapping("/findById/{boardId}")
    public Optional<Board> findById(@PathVariable String boardId) {
        return service.findById(boardId);
    }

    @GetMapping("/existsById/{board}")
    public boolean existsById(@PathVariable String board) {
        return service.existsById(board);
    }

}
