package kr.co.clozet.users.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : kr.co.clozet.users.controllers
 * fileName       : UsersController
 * author         : kimyunseop
 * date           : 2022-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06        kimyunseop       최초 생성
 */
@RequestMapping("/u")
@RequiredArgsConstructor
@RestController
public class UsersController {
    @PostMapping("/u")
    public ResponseEntity<U> save(@RequestBody U u){
        return ResponseEntity.ok();
    }

}
