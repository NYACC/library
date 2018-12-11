package cn.albumenj.dto;

import cn.albumenj.bean.Library;
import cn.albumenj.bean.UserSecurity;
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
