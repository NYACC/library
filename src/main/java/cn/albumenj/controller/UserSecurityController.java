package cn.albumenj.controller;

import cn.albumenj.bean.UserSecurity;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.UserSecurityService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/usersecurity")
public class UserSecurityController {
    @Autowired
    UserSecurityService userSecurityService;

    @RequestMapping (path = "/login", method = RequestMethod.POST)
    public PageCodeDto login(@RequestBody UserSecurity userSecurity){
        UserSecurity userSecurityDto = userSecurityService.login(userSecurity);
        PageCodeDto pageCodeDto;
        if(userSecurityDto != null) {
            userSecurityDto.setPassword(null);
            pageCodeDto = new PageCodeDto(PageCodeEnum.LOGIN_SUCCESS,userSecurityDto);
        }
        else {
            pageCodeDto = new PageCodeDto(PageCodeEnum.LOGIN_FAILED);
        }
        return pageCodeDto;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.add(userSecurityService.addUser(userSecurity));
    }

    @RequestMapping(path = "/modify", method = RequestMethod.POST)
    public PageCodeDto modify(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.update(userSecurityService.modifyUser(userSecurity));
    }

    @RequestMapping(path = "/getlist", method = RequestMethod.POST)
    public PageCodeDto getList(@RequestBody UserSecurity userSecurity) {
        return PageCodeUtil.get(userSecurityService.getList(userSecurity));
    }

    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public PageCodeDto count(UserSecurity userSecurity) {
        return PageCodeUtil.get(userSecurityService.count(userSecurity));
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public PageCodeDto delete(UserSecurity userSecurity) {
        return PageCodeUtil.delete(userSecurityService.delete(userSecurity));
    }
}
