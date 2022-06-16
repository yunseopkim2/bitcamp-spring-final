package kr.co.clozet.colors.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.colors.domains.Color;
import kr.co.clozet.colors.repositories.ColorRepository;
import kr.co.clozet.colors.services.ColorService;
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
 * fileName        :ColorServiceImpl.java
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
public class ColorServiceImpl implements ColorService {

    private final ColorRepository repository;
    @Override
    public List<Color> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Color> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Color> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Messenger count() {
        return Messenger.builder().message(string(repository.count())).build();
    }

    @Override
    public Messenger delete(Color color) {
        repository.delete(color);
        return Messenger.builder().build();
    }

    @Override
    public Messenger save(Color color) {
        return null;
    }

    @Override
    public Optional<Color> findByDate(String date) {
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
    public Optional<Color> findByUserId(String userId) {
        return null;
    }

    @Override
    public Messenger update(Color color) {
        return null;
    }
}
