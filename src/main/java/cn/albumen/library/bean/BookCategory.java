package cn.albumen.library.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Albumen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookCategory extends BaseBean {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
