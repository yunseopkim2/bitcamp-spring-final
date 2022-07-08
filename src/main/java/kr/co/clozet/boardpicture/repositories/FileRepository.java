package kr.co.clozet.boardpicture.repositories;

import kr.co.clozet.boardpicture.domains.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : kr.co.clozet.boardpicture.repositories
 * fileName       : BoardPictureRepository
 * author         : kimyunseop
 * date           : 2022-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-08        kimyunseop       최초 생성
 */
@Repository
public interface FileRepository extends JpaRepository<File,Long> {

}
