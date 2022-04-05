package xyz.xcye.common.entity.email;

import lombok.Data;

/**
 * 邮箱验证账户实体
 * @author qsyyke
 */

@Data
public class EmailVerifyAccount {
    /**
     * 验证邮箱的地址
     */
    private String verifyAccountUrl;

    /**
     * 接受者的邮箱
     */
    private String receiverEmail;

    /**
     * 此验证链接失效时间
     */
    private String expirationTime;
}
