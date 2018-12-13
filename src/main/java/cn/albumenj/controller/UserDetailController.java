package cn.albumenj.controller;

import cn.albumenj.annotation.ControllerLog;
import cn.albumenj.bean.UserDetail;
import cn.albumenj.constant.LevelConst;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.UserDetailService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/userdetail")
public class UserDetailController {
    @Autowired
    UserDetailService userDetailService;

    @ControllerLog(description = "SelectUserDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectById",method = RequestMethod.GET)
    public PageCodeDto selectById(UserDetail userDetail) {
        return PageCodeUtil.get(userDetailService.selectById(userDetail));
    }

    @ControllerLog(description = "SelectUserDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectList",method = RequestMethod.GET)
    public PageCodeDto selectList(UserDetail userDetail) {
        return PageCodeUtil.get(userDetailService.selectByPermission(userDetail));
    }

    @ControllerLog(description = "UpdateUserDetail",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody UserDetail userDetail) {
        return PageCodeUtil.update(userDetailService.update(userDetail));
    }
}
