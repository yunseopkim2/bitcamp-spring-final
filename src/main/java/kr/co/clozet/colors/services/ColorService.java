package kr.co.clozet.colors.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.colors.domains.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.clothes.services
 * fileName        :ColorService.java
 * author          : kimyunseop
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           kimyunseop      최초 생성
 **/
public interface ColorService {
    List<Color> findAll();
    List<Color> findAll(Sort sort);
    Page<Color> findAll(Pageable pageable);

    Messenger count();

    Messenger delete(Color color);

    Messenger save(Color color);

    Optional<Color> findByDate(String date);

    Messenger existsById(String userid);

    Messenger getOne(Long id);
    //custom

    Optional<Color> findByUserId(String userId);

    Messenger update(Color color);
}
