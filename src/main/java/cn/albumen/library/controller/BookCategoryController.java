package cn.albumen.library.controller;

import cn.albumen.library.annotation.ControllerLog;
import cn.albumen.library.bean.BookCategory;
import cn.albumen.library.constant.LevelConst;
import cn.albumen.library.dto.PageCodeDto;
import cn.albumen.library.service.BookCategoryService;
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
@RequestMapping("/bookcategory")
public class BookCategoryController {
    @Autowired
    BookCategoryService bookCategoryService;

    @ControllerLog(description = "AddBookCategory", level = LevelConst.CRITICAL)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.add(bookCategoryService.add(bookCategory));
    }

    @ControllerLog(description = "DeleteBookCategory", level = LevelConst.CRITICAL)
    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public PageCodeDto delete(BookCategory bookCategory) {
        return PageCodeUtil.delete(bookCategoryService.delete(bookCategory));
    }

    @ControllerLog(description = "UpdateBookCategory", level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.update(bookCategoryService.update(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory", level = LevelConst.INFO)
    @RequestMapping(path = "/selectall", method = RequestMethod.GET)
    public PageCodeDto selectAll(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectAll(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory", level = LevelConst.INFO)
    @RequestMapping(path = "/selectlimit", method = RequestMethod.GET)
    public PageCodeDto selectLimit(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectLimit(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory", level = LevelConst.INFO)
    @RequestMapping(path = "/selectbyid", method = RequestMethod.GET)
    public PageCodeDto selectById(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectById(bookCategory));
    }
}
