package cn.albumen.library.dto;

import cn.albumen.library.bean.BookDetail;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Albumen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSearchDto extends BookDetail {
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
