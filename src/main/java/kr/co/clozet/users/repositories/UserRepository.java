package kr.co.clozet.users.repositories;

import kr.co.clozet.users.domains.User;
import kr.co.clozet.users.domains.UserDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.repositories
 * fileName        :UserRepository.java
 * author          : kimyunseop
 * date            :2022-05-03
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-03           kimyunseop      최초 생성
 **/

interface UserCustomRepository{
    // 000. 사용자의 비밀번호를 수정하시오
    @Query(value = "select u.username from User u")
    public String [] selectAllJPQL();

    @Query("select u.username from User u where u.name= :name and u.email= :email")
    public String find_id(@Param("name") String name, @Param("email") String email);
}

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository{
    Optional<User> findByUsername(String username);

}

