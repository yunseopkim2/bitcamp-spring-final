package kr.co.clozet.clothes.services;

import kr.co.clozet.auth.domains.Messenger;
import kr.co.clozet.clothes.domains.Clothes;
import kr.co.clozet.clothes.repositories.ClothesRepository;
import kr.co.clozet.clothes.services.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static kr.co.clozet.common.lambdas.Lambda.string;

/**
 * packageName:kr.co.clozet.closet.services
 * fileName        :ClothesServiceImpl.java
 * author          : kimyunseop
 * date            :2022-05-29
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-05-29           kimyunseop      최초 생성
 **/
@Service
@RequiredArgsConstructor
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository repository;
    @Override
    public List<Clothes> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Clothes> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Page<Clothes> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
       return repository.count();
    }

    @Override
    public Messenger delete(Clothes clothes) {
        repository.delete(clothes);
        return Messenger.builder().message("삭제되었습니다.").build();
    }

    @Override
    public Messenger save(Clothes clothes) {
        return null;
    }

    @Override
    public Optional<Clothes> findByDate(String date) {
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
    public Optional<Clothes> findByUserId(String userId) {
        return null;
    }

    @Override
    public Messenger update(Clothes clothes) {
        return null;
    }
}
