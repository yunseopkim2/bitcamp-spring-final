package kr.co.clozet.closets.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.closets.domains.Closet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.closet.services
 * fileName        :ClosetService.java
 * author          : kimyunseop
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           kimyunseop      최초 생성
 **/
public interface ClosetService {

    List<Closet> findAll();
    List<Closet> findAll(Sort sort);
    Page<Closet> findAll(Pageable pageable);

    Messenger count();

    Messenger delete(Closet closet);

    Messenger save(Closet closet);

    Optional<Closet> findByDate(String date);

    Messenger getOne(Long id);
    //custom

    Optional<Closet> findByUserId(String userId);

    Messenger update(Closet closet);
}
