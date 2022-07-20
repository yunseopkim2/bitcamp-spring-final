package kr.co.clozet.clothes.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * packageName:kr.co.clozet.clothes.domains
 * fileName        :ClothesDTO.java
 * author          : kimyunseop
 * date            :2022-06-15
 * desc            :
 * =============================================
 * DATE              AUTHOR        NOTE
 * =============================================
 * 2022-06-15           kimyunseop      최초 생성
 **/
@Component @Data
public class ClothesDTO {
    @ApiModelProperty(position = 1) private long clothesId;
    @ApiModelProperty(position = 2) String userId;
    @ApiModelProperty(position = 4) String clothesClassification;
    @ApiModelProperty(position = 5) String colors;
    @ApiModelProperty(position = 6) String weathers;
    @ApiModelProperty(position = 7) String styles;
    @ApiModelProperty(position = 8) String events;
    @ApiModelProperty(position = 9) String token;
}
