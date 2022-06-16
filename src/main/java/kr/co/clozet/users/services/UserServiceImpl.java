package kr.co.clozet.users.services;

import kr.co.clozet.auth.configs.AuthProvider;
import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.auth.exception.SecurityRuntimeException;
import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import kr.co.clozet.users.repositories.UserRepository;
import kr.co.clozet.common.dataStructure.Box;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static kr.co.clozet.common.lambdas.Lambda.*;

@RequiredArgsConstructor
@Service
@Log
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthProvider provider;
    private final ModelMapper modelMapper;
    //모델이랑 엔티티를 바꿔주는것

    @Override
    public UserDTO login(UserDTO paramUser) {
        try{
            UserDTO returnUser = new UserDTO();
            String username = paramUser.getUsername();
            User findUser = repository.findByUsername(username).orElse(null);
            if (findUser != null){
                boolean checkPassword = encoder.matches(paramUser.getPassword(), findUser.getPassword());
                if (checkPassword){
                    returnUser = modelMapper.map(findUser, UserDTO.class);
                    String token = provider.createToken(username, returnUser.getRoles());
                    returnUser.setToken(token);

                }else {
                    String token = "FAILURE";
                    returnUser.setToken(token);
                }
            }else {
                returnUser.setToken("FAILURE");
            }
            return returnUser;
        }catch (Exception e){
            throw new SecurityRuntimeException("유효하지 않은 아이디/비밀번호", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Messenger logout(HttpServletRequest request){
        log.info("logout Method 진입");
        HttpSession session = request.getSession();
        session.invalidate();
        return Messenger.builder().message("로그아웃 완료").build();
    }


    @Override
    public List<User> findAll() {return repository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();

    }

    @Override
    public Messenger update(User user) {
        final Optional<User> original = repository.findById(user.getUserId());
        original.ifPresent(user1 -> {
            User.builder().userId(user.getUserId())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .birth(user.getBirth())
                    .username(user.getUsername())
                    .build();
            repository.save(user1);
        });
        return Messenger.builder().message("업데이트 완료").build();
    }

    @Override
    public void removeUser(String userId) {

    }

    @Override
    public Messenger delete(User user) {
        try{repository.delete(user);}
        catch (Exception e){
            log.info("error deleting entity");
            throw new RuntimeException("error deleting entity"+ user.getUserId());
        }
        return Messenger.builder().message("삭제 완료").build();
    }

    @Override
    public Messenger save(UserDTO user) {
        Optional<User> users = repository.findById(1L);

        users.ifPresent(selectUser-> {selectUser.getUserId();
            selectUser.getBirth();
            selectUser.getUsername();
            selectUser.getEmail();
            selectUser.getName();
            selectUser.getPassword();
            selectUser.getPhone();
            repository.save(selectUser);
        });
        return Messenger.builder().message("저장되었습니다.").build();
    }


    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public Messenger existsById(String userid) {
        return repository.existsById(longParse(userid))
                ? Messenger.builder().message("EXIST").build()
                : Messenger.builder().message("NOT_EXIST").build();
    }

    @Override
    public Messenger getOne(Long id) {
        return null;
    }

    @Override
    public Messenger change(User user) {
        Optional<User> updateUser = repository.findById(user.getUserId());
        updateUser.ifPresent(selectUser ->{
            selectUser.getUserId();
            selectUser.getBirth();
            selectUser.getUsername();
            selectUser.getEmail();
            selectUser.getName();
            selectUser.getPassword();
            selectUser.getPhone();
            repository.save(selectUser);

        });
        return Messenger.builder().build();
    }

    @Override
    public List<User> findByUserName(String username) {
        List<User> ls = repository.findAll();
        Box<String, User> box = new Box<>();
        //ls = box.findByUserName(ls, name);
        //ls.stream().filter(...);
        return null;

    }

}

