package kr.co.clozet.boardpicture.services;

import kr.co.clozet.boardpicture.domains.File;
import kr.co.clozet.boardpicture.domains.FileDTO;
import kr.co.clozet.boardpicture.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * packageName    : kr.co.clozet.boardpicture.services
 * fileName       : FileService
 * author         : kimyunseop
 * date           : 2022-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-08        kimyunseop       최초 생성
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public Long saveFile(FileDTO fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDTO getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDTO fileDto = FileDTO.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }
}
