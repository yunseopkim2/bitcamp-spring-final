package kr.co.clozet.boards.repositories;

import kr.co.clozet.boards.domains.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * packageName:kr.co.clozet.repositories
 * fileName        :BoardRepository.java
 * author          : kimyunseop
 * date            :2022-05-04
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-04           kimyunseop      최초 생성
 **/

interface BoardCustomRepository{
    /// 000. 게시판 이름 boardName 을 변경하시오
    @Query("select b from Board b left join Article a ON a.user.userId ")
    Page<Board> findUsersQnaPagingAndSort(Pageable pageable);
}

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
