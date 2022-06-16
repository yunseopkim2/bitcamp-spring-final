package kr.co.clozet.styles.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.events.domains.Event;
import kr.co.clozet.styles.domains.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.clothes.services
 * fileName        :StyleService.java
 * author          : kimyunseop
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           kimyunseop      최초 생성
 **/
public interface StyleService {
    List<Style> findAll();
    List<Style> findAll(Sort sort);
    Page<Style> findAll(Pageable pageable);

    Messenger count();

    Messenger delete(Style style);

    Messenger save(Style style);

    Optional<Style> findByDate(String date);

    Messenger existsById(String userid);

    Messenger getOne(Long id);
    //custom

    Optional<Style> findByUserId(String userId);

    Messenger update(Style style);
}
