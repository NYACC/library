package cn.albumenj.controller;

import cn.albumenj.bean.Library;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.LibraryDto;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.LibraryService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody Library library) {
        return PageCodeUtil.add(libraryService.add(library));
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public PageCodeDto delete(Library library) {
        return PageCodeUtil.delete(libraryService.delete(library));
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody Library library) {
        return PageCodeUtil.update(libraryService.update(library));
    }

    @RequestMapping(path = "/selectbycurator",method = RequestMethod.GET)
    public PageCodeDto selectByCurator(Library library) {
        return PageCodeUtil.get(libraryService.selectByCurator(library));
    }

    @RequestMapping(path = "/count",method = RequestMethod.GET)
    public PageCodeDto count(Library library) {
        return PageCodeUtil.get(libraryService.count(library));
    }

    @RequestMapping(path = "/select", method = RequestMethod.GET)
    public PageCodeDto selectLimited(Library library) {
        return PageCodeUtil.get(libraryService.selectLimit(library));
    }
}
