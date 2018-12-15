package cn.albumen.library.controller;

import cn.albumen.library.annotation.ControllerLog;
import cn.albumen.library.bean.UserDetail;
import cn.albumen.library.constant.LevelConst;
import cn.albumen.library.dto.PageCodeDto;
import cn.albumen.library.service.UserDetailService;
import cn.albumen.library.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/userdetail")
public class UserDetailController {
    @Autowired
    UserDetailService userDetailService;

    @ControllerLog(description = "SelectUserDetail", level = LevelConst.INFO)
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public PageCodeDto selectById(UserDetail userDetail) {
        return PageCodeUtil.get(userDetailService.selectById(userDetail));
    }

    @ControllerLog(description = "SelectUserDetail", level = LevelConst.INFO)
    @RequestMapping(path = "/selectList", method = RequestMethod.GET)
    public PageCodeDto selectList(UserDetail userDetail) {
        return PageCodeUtil.get(userDetailService.selectByPermission(userDetail));
    }

    @ControllerLog(description = "UpdateUserDetail", level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody UserDetail userDetail) {
        return PageCodeUtil.update(userDetailService.update(userDetail));
    }
}
