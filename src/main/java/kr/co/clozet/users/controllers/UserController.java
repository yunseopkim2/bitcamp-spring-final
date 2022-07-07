package kr.co.clozet.users.controllers;


import io.swagger.annotations.*;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.users.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*") // 추가
@Api(tags = "users") // swagger api 추가
@RequestMapping("/users")
@RestController
@Log
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
    @RequestMapping(value = "/findPw", method = RequestMethod.GET)
    public void findPwGET() throws Exception{
    }

    @PostMapping(value = "/findPw")
    public void findPwPOST(@RequestBody UserDTO user, HttpServletResponse response) throws Exception{
        System.out.println("아이디 : " + user.getUsername());
        System.out.println("email : " + user.getEmail());
        service.findPw(response, user);
    }
  /*  @PostMapping("/findPassword")
    public String findPassword(@RequestBody UserDTO userDTO) throws Exception{
        System.out.println("폼에서 받아온 email 값 : " + userDTO);
        return service.findPassword(userDTO);
    }*/

    @GetMapping("/existsByUsername") @ResponseBody
    public ResponseEntity<Boolean> existsByUsername(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByUsername(userDTO.getUsername()));
    }
    @GetMapping("/existsByPhone") @ResponseBody
    public ResponseEntity<Boolean> existsByPhone(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByPhone(userDTO.getPhone()));
    }
    @GetMapping("/existsByEmail") @ResponseBody
    public ResponseEntity<Boolean> existsByEmail(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByEmail(userDTO.getEmail()));
    }
    @GetMapping("/existsByNickname") @ResponseBody
    public ResponseEntity<Boolean> existsByNickname(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(repository.existsByNickname(userDTO.getNickname()));
    }
    @PostMapping("/findUser")
    @ResponseBody
    public ResponseEntity<String> find_id(@RequestBody UserDTO userDTO) {
        //System.out.println("정보"+ service.find_id(userDTO).toString());
        return ResponseEntity.ok(service.find_id(userDTO).getUsername());
    }

    @PutMapping("/change")
    public ResponseEntity<Optional<User>> changeInfo(HttpSession session, @RequestBody User user)
    {
        return null;
    }

    @RequestMapping(value ="/logout", method = RequestMethod.GET)
    public ResponseEntity<Messenger> logout(HttpServletRequest request) {return ResponseEntity.ok(service.logout(request));}

    @PatchMapping(value = "/update") @ResponseBody
    public void partialUpdate(@RequestBody UserDTO userDTO) throws Exception{
       service.partialUpdate(userDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/findAll/sort")
    public ResponseEntity<List<User>> findAll(Sort sort) {return ResponseEntity.ok(service.findAll(sort));}

    @GetMapping("/findAll/pageable")
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));}

    @GetMapping("/user")
    public ResponseEntity<String []> findUser() {return ResponseEntity.ok(repository.selectAllJPQL());}

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {return ResponseEntity.ok(service.count());}

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> delete(@RequestBody UserDTO user ) {
        return ResponseEntity.ok(service.delete(user));}

    @DeleteMapping("/deleteAll")
    public Messenger deleteAll( ) {
        return service.deleteAll();}

    @GetMapping("/findById/{userId}")
    public ResponseEntity<User> findById(@PathVariable long userId) {
        return ResponseEntity.ok(service.findById(userId));}

    @GetMapping("/existsById/{username}")
    public ResponseEntity<Messenger> existsById(@PathVariable String username) {
        return ResponseEntity.ok(service.existsById(username));}

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Messenger> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));}
    @PostMapping(value = "/getToken") @ResponseBody
    public ResponseEntity<UserDTO> getToken(@RequestBody UserDTO userDTO) throws Exception{
        return ResponseEntity.ok(service.save1(userDTO));
    }
    @PutMapping("/updates")
    public ResponseEntity<Messenger> update(@RequestBody User user) {

        return ResponseEntity.ok(service.update(user));
    }
    @PostMapping("/token") @ResponseBody
    public ResponseEntity<Optional<User>> findByToken(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findByToken(userDTO));
    }
    @PostMapping("/BytokenArticle") @ResponseBody
    public ResponseEntity<List<Article> > findByTokenToArticle(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.findByTokenToArticle(userDTO));
    }

}




