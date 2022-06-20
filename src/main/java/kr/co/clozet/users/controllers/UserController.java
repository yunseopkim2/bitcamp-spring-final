package kr.co.clozet.users.controllers;

import io.swagger.annotations.*;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*") // 추가
@Api(tags = "users") // swagger api 추가
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService service;
    private final UserRepository repository;

    @PostMapping("/join")
    @ApiOperation(value = "${UserController.join}") // 리액트에서 PostMapping해서, 다음 여기로 옴 (추가)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 403, message = "승인거절"),
            @ApiResponse(code = 427, message = "중복 ID")
    })
    public ResponseEntity<Messenger> save(@ApiParam("Join User") @RequestBody UserDTO user) { // User에서 UserDTO로 변경 + ApiParam 추가
        System.out.println("회원가입 정보: " + user.toString()); // 나중에 지워야됨 회원가입 정보를 띄우면 안됨
        return ResponseEntity.ok(service.save(user)); }

    @PostMapping("/login")
    @ApiOperation(value = "${UserController.login}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something Wrong"),
            @ApiResponse(code = 422, message = "유효하지 않는 아이디/비밀번호")
    })
    public ResponseEntity<UserDTO> login(@ApiParam("Login User")@RequestBody UserDTO user) {
//        return ResponseEntity.ok(service.login(modelMapper.map(user, User.class)));
        return ResponseEntity.ok(service.login(user));

    }
    @PutMapping("/change")
    public ResponseEntity<Optional<User>> changeInfo(@RequestParam Long userId, @RequestBody User user){
        return null;
    }
    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public ResponseEntity<Messenger> logout(HttpServletRequest request) {return ResponseEntity.ok(service.logout(request));}

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<User>> findAll(Sort sort) {return ResponseEntity.ok(service.findAll(sort));}

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));}

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody User user) {
        return ResponseEntity.ok(service.delete(user));}


    @GetMapping("/findById/{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findById(userId));}

    @GetMapping("/existsById/{username}")
    public ResponseEntity<Messenger> existsById(@PathVariable String username) {
        return ResponseEntity.ok(service.existsById(username));}

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Messenger> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));}
    @PutMapping("/update")
    public ResponseEntity<Messenger> update(@RequestBody User user) {

        return ResponseEntity.ok(service.update(user));
    }




}




