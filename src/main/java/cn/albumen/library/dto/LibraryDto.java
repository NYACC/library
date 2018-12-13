package cn.albumen.library.dto;

import cn.albumen.library.bean.Library;
import cn.albumen.library.bean.UserSecurity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Albumen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryDto extends Library {
    private UserSecurity curator;

    public UserSecurity getCurator() {
        return curator;
    }

    public void setCurator(UserSecurity curator) {
        this.curator = curator;
    }
}
