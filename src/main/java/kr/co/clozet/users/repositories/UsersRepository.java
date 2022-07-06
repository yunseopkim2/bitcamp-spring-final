package kr.co.clozet.users.repositories;

import kr.co.clozet.users.domains.U;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : kr.co.clozet.users.repositories
 * fileName       : UsersRepository
 * author         : kimyunseop
 * date           : 2022-07-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-06        kimyunseop       최초 생성
 */
@Repository
public interface UsersRepository extends JpaRepository<U, Long> {
}
