package cn.albumen.library.controller;

import cn.albumen.library.annotation.ControllerLog;
import cn.albumen.library.bean.Library;
import cn.albumen.library.constant.LevelConst;
import cn.albumen.library.dto.PageCodeDto;
import cn.albumen.library.service.LibraryService;
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
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @ControllerLog(description = "AddLibrary",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody Library library) {
        return PageCodeUtil.add(libraryService.add(library));
    }

    @ControllerLog(description = "DeleteLibrary",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public PageCodeDto delete(Library library) {
        return PageCodeUtil.delete(libraryService.delete(library));
    }

    @ControllerLog(description = "UpdateLibrary",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody Library library) {
        return PageCodeUtil.update(libraryService.update(library));
    }

    @ControllerLog(description = "SelectLibrary",level = LevelConst.INFO)
    @RequestMapping(path = "/selectbycurator",method = RequestMethod.GET)
    public PageCodeDto selectByCurator(Library library) {
        return PageCodeUtil.get(libraryService.selectByCurator(library));
    }

    @ControllerLog(description = "CountLibrary",level = LevelConst.INFO)
    @RequestMapping(path = "/count",method = RequestMethod.GET)
    public PageCodeDto count(Library library) {
        return PageCodeUtil.get(libraryService.selectCount(library));
    }

    @ControllerLog(description = "SelectLibrary",level = LevelConst.INFO)
    @RequestMapping(path = "/select", method = RequestMethod.GET)
    public PageCodeDto selectLimited(Library library) {
        return PageCodeUtil.get(libraryService.selectLimit(library));
    }
}
