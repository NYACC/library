package cn.albumen.library.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Albumen
 */
public class PermissionConst {
    public final static Integer ADMINISTRATOR = 1;
    public final static Integer CURATOR = 2;
    public final static Integer CATEGORY_STAFF = 3;
    public final static Integer COMMON = 4;
    public final static Map<Integer, String> PERMISSION = new HashMap<Integer, String>() {
        {
            put(1, "ADMINISTRATOR");
            put(2, "CURATOR");
            put(3, "CATEGORY_STAFF");
            put(4, "COMMON");
        }
    };
}
