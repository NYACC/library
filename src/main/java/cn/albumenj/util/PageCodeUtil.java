package cn.albumenj.util;

import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;

/**
 * @author Albumen
 */
public class PageCodeUtil {
    public static PageCodeDto get(Object o) {
        if(o instanceof Integer) {
            if(((Integer) o).compareTo(0)==0){
                return new PageCodeDto(PageCodeEnum.GET_FAILED);
            }
            else {
                return new PageCodeDto(PageCodeEnum.GET_SUCCESS,o);
            }
        }
        else {
            if (o != null) {
                return new PageCodeDto(PageCodeEnum.GET_SUCCESS, o);
            } else {
                return new PageCodeDto(PageCodeEnum.GET_FAILED);
            }
        }
    }

    public static PageCodeDto add(boolean flag) {
        if(flag) {
            return new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.ADD_FAILED);
        }
    }

    public static PageCodeDto delete(boolean flag) {
        if(flag) {
            return new PageCodeDto(PageCodeEnum.DELETE_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.DELETE_FAILED);
        }
    }

    public static PageCodeDto update(boolean flag) {
        if(flag) {
            return new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.MODIFY_FAILED);
        }
    }
}
