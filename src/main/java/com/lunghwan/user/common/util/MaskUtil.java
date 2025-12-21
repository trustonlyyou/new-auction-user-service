package com.lunghwan.user.common.util;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 마스킹 관련 유틸
 */
@Slf4j
public class MaskUtil {

    /**
     * 사용자 이름 마스킹
     * @param name 이름
     * @return String
     */
    public static String maskName(String name) {
        if (!StringUtils.hasText(name)) {
            log.warn(" - 사용자 이름 마스킹 중 오류 발생 >> 이름이 비어있습니다.");
            throw new UserApiException(ErrorCode.INVALID_INPUT_VALUE, "이름이 비어있습니다.");
        }

        if (name.length() <= 2) {
            return name.charAt(0) + "*";
        }

        return name.charAt(0) + "*" + name.charAt(name.length() - 1);
    }

    /**
     * 사용자 핸드폰 마스킹
     * @param phone 핸드폰 번호
     * @return String
     */
    public static String maskPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            log.warn(" - 사용자 핸드폰 번호 마스킹 중 오류 발생 >> 핸드폰 번호가 비어있습니다.");
            throw new UserApiException(ErrorCode.INVALID_INPUT_VALUE, "핸드폰 번호가 비어있습니다.");
        }

        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}
