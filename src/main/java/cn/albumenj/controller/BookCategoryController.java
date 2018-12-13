package cn.albumenj.controller;

import cn.albumenj.annotation.ControllerLog;
import cn.albumenj.bean.BookCategory;
import cn.albumenj.constant.LevelConst;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.BookCategoryService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/bookcategory")
public class BookCategoryController {
    @Autowired
    BookCategoryService bookCategoryService;

    @ControllerLog(description = "AddBookCategory",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.add(bookCategoryService.add(bookCategory));
    }

    @ControllerLog(description = "DeleteBookCategory",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public PageCodeDto delete(BookCategory bookCategory) {
        return PageCodeUtil.delete(bookCategoryService.delete(bookCategory));
    }

    @ControllerLog(description = "UpdateBookCategory",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.update(bookCategoryService.update(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory",level = LevelConst.INFO)
    @RequestMapping(path = "/selectall",method = RequestMethod.GET)
    public PageCodeDto selectAll(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectAll(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory",level = LevelConst.INFO)
    @RequestMapping(path = "/selectlimit",method = RequestMethod.GET)
    public PageCodeDto selectLimit(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectLimit(bookCategory));
    }

    @ControllerLog(description = "SelectBookCategory",level = LevelConst.INFO)
    @RequestMapping(path = "/selectbyid",method = RequestMethod.GET)
    public PageCodeDto selectById(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectById(bookCategory));
    }
}
