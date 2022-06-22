package kr.co.clozet.users.domains;

import com.sun.istack.NotNull;
import kr.co.clozet.articles.domains.Article;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.users.repositories.UserRepository;
import lombok.*;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName:kr.co.clozet.domains
 * fileName        :User.java
 * author          : kimyunseop
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           kimyunseop      최초 생성
 **/
@ToString
@Setter // modelMapper 를 사용하기 위해
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 컴포넌트는 프로퍼티와 메소드의 집합
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue private long userId;
    @Column private @NotNull String username;
    @Column private @NotNull String password;
    @Column private  String name;
    @Column private  String birth;
    @Column private  String nickname;
    @Column private  String email;
    @Column private  String phone;
    @Column private String token;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Clothes> clothes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;


}
