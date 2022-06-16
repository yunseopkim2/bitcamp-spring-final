package kr.co.clozet.events.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.colors.domains.Color;
import kr.co.clozet.events.domains.Event;
import kr.co.clozet.events.repositories.EventRepository;
import kr.co.clozet.events.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static kr.co.clozet.common.lambdas.Lambda.string;

/**
 * packageName:kr.co.clozet.clothes.services
 * fileName        :EventServiceImpl.java
 * author          : kimyunseop
 * date            :2022-06-07
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-07           kimyunseop      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Event> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();
    }

    @Override
    public Messenger delete(Event event) {
        repository.delete(event);
        return Messenger.builder().build();
    }

    @Override
    public Messenger save(Event event) {
        return null;
    }

    @Override
    public Optional<Event> findByDate(String date) {
        return Optional.empty();
    }

    @Override
    public Messenger existsById(String userid) {
        return null;
    }

    @Override
    public Messenger getOne(Long id) {
        return null;
    }

    @Override
    public Optional<Event> findByUserId(String userId) {
        return null;
    }

    @Override
    public Messenger update(Event event) {
        return null;
    }
}
