package kr.co.clozet.clothes.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.closet.services
 * fileName        :ClothesService.java
 * author          : kimyunseop
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           kimyunseop      최초 생성
 **/
public interface ClothesService {
    List<Clothes> findAll();
    List<Clothes> findAll(Sort sort);
    Page<Clothes> findAll(Pageable pageable);

    Messenger count();

    Messenger delete(Clothes clothes);

    Messenger save(Clothes clothes);

    Optional<Clothes> findByDate(String date);

    Messenger existsById(String userid);

    Messenger getOne(Long id);
    //custom

    Optional<Clothes> findByUserId(String userId);

    Messenger update(Clothes clothes);
}
