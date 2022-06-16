package kr.co.clozet.styles.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.events.domains.Event;
import kr.co.clozet.styles.domains.Style;
import kr.co.clozet.styles.repositories.StyleRepository;
import kr.co.clozet.styles.services.StyleService;
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
 * fileName        :StyleServiceImpl.java
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
public class StyleServiceImpl implements StyleService {

    private final StyleRepository repository;
    @Override
    public List<Style> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Style> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Style> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();
    }

    @Override
    public Messenger delete(Style style) {
        repository.delete(style);
        return Messenger.builder().build();
    }

    @Override
    public Messenger save(Style style) {
        return null;
    }

    @Override
    public Optional<Style> findByDate(String date) {
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
    public Optional<Style> findByUserId(String userId) {
        return null;
    }

    @Override
    public Messenger update(Style style) {
        return null;
    }
}
