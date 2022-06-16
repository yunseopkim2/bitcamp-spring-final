package kr.co.clozet.events.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.colors.domains.Color;
import kr.co.clozet.events.domains.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * packageName:kr.co.clozet.clothes.services
 * fileName        :EventService.java
 * author          : kimyunseop
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           kimyunseop      최초 생성
 **/
public interface EventService {
    List<Event> findAll();
    List<Event> findAll(Sort sort);
    Page<Event> findAll(Pageable pageable);

    Messenger count();

    Messenger delete(Event event);

    Messenger save(Event event);

    Optional<Event> findByDate(String date);

    Messenger existsById(String userid);

    Messenger getOne(Long id);
    //custom

    Optional<Event> findByUserId(String userId);

    Messenger update(Event event);
}
