package xyz.xcye.entity.table;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import xyz.xcye.enums.FieldLengthEnum;
import xyz.xcye.valid.Delete;
import xyz.xcye.valid.Update;
import xyz.xcye.valid.validator.Status;
import xyz.xcye.valid.validator.ValidateString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

/**
 * 数据表 au_article
 * @author qsyyke
 */

@Data
public class Article {
    /**
     * 唯一uid 不为null 主键
     */
    @NotNull(groups = {Delete.class, Update.class})
    private BigInteger uid;

    /**
     * 1：展示评论 0：不显示评论
     */
    @Status(value = "文章-展示评论")
    private int showComment;

    /**
     * 文章中如果有附件的话，存放附件集合 可以为null
     * <p>length < 255</p>
     */
    @Length(max = FieldLengthEnum.STRING_ARRAY,message = "文章-附件下载集合长度必须小于{max}")
    private String downloadFileArr;

    /**
     * 文章类别的uid集合 可以为null
     * <p>length < 255</p>
     */
    @Length(max = FieldLengthEnum.STRING_ARRAY,message = "文章-类别集合长度必须小于{max}")
    private String categoryArr;

    /**
     * 文章的标签uid集合 可以为null
     * <p>length < 255</p>
     */
    @Length(max = FieldLengthEnum.STRING_ARRAY,message = "文章-标签集合长度必须小于{max}")
    private String tagArr;

    /**
     * 文章是否发布 1：已发布 0：未发布 不能为null
     */
    @Status(value = "文章-发布")
    private int publishStatus;

    /**
     * 该篇文章是哪个用户发布的 可以为null
     */
    private BigInteger userUid;

    /**
     * 是否是原创 1：原创 0：不是原创
     */
    @Status("文章-原创")
    private int originalArticle;

    /**
     * 如果不是原创的话 该篇文章的真是地址
     * <p>length < 255</p>
     */
    @Length(max = FieldLengthEnum.URL,message = "文章-路径长度必须小于{max}")
    private String originalArticleUrl;

    /**
     * 该篇文章对应的评论uid集合 可以为null
     * <p>mysql -> text</p>
     */
    @Length(max = FieldLengthEnum.STRING_ARRAY,message = "文章-对应评论集合")
    private String commentArr;

    /**
     * 最后修改时间，不能为null
     * <p>mysql -> datetime</p>
     */
    @ValidateString(max = FieldLengthEnum.TIME_FORMAT,value = "文章-最后更新时间")
    private String updatedAt;

    /**
     * 文章发布时间 不能为null
     * <p>mysql -> datetime</p>
     */
    @ValidateString(value = "文章-创建时间",max = FieldLengthEnum.TIME_FORMAT)
    private String createdAt;

    /**
     * 文章的内容 不能为null
     * <p>mysql -> longtext</p>
     */
    @ValidateString(value = "文章-内容",max = FieldLengthEnum.CONTENT)
    private String content;

    /**
     * 文章点赞数 不能为null
     */
    @Pattern(regexp = "^[0-9]?\\d*$",message = "文章-点赞数量，必须是整数")
    private int likeNumber;

    /**
     * 文章的标题 不能为null
     * <p>length < 100</p>
     */
    @ValidateString(value = "文章-标题",max = FieldLengthEnum.TITLE)
    private String title;

    /**
     * 文章的简介 可以为null
     * <p>length < 500</p>
     */
    @Length(max = FieldLengthEnum.SUMMARY,message = "文章-简介必须小于等于{max}")
    private String summary;

    /**
     * 是否定时发布 1：定时 0：不定时 不能为null
     */
    @Status("文章-开启定时发布功能的")
    private int isTiming;

    /**
     * 如果是定时发布的话，定时发布的时间 可以为null
     * <p>mysql -> datetime</p>
     */
    @Length(message = "文章-定时发布时间，只能是未来",max = FieldLengthEnum.TIME_FORMAT)
    private String timingPublish;

    /**
     * 文章的状态 1：已删除 0：未删除
     */
    @Status("文章-删除")
    private int deleteStatus;

    /**
     * 文章的封面url 不可以为null
     * <p>length < 255</p>
     */
    @ValidateString(value = "文章-封面图",max = FieldLengthEnum.URL)
    private String coverPicture;
}
