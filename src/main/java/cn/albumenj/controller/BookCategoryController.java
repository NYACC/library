package cn.albumenj.controller;

import cn.albumenj.bean.BookCategory;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.BookCategoryService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/bookcategory")
public class BookCategoryController {
    @Autowired
    BookCategoryService bookCategoryService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.add(bookCategoryService.add(bookCategory));
    }

    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public PageCodeDto delete(BookCategory bookCategory) {
        return PageCodeUtil.delete(bookCategoryService.delete(bookCategory));
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody BookCategory bookCategory) {
        return PageCodeUtil.update(bookCategoryService.udpate(bookCategory));
    }

    @RequestMapping(path = "/selectall",method = RequestMethod.GET)
    public PageCodeDto selectAll(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectAll(bookCategory));
    }

    @RequestMapping(path = "/selectlimit",method = RequestMethod.GET)
    public PageCodeDto selectLimit(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectLimit(bookCategory));
    }

    @RequestMapping(path = "/selectbyid",method = RequestMethod.GET)
    public PageCodeDto selectById(BookCategory bookCategory) {
        return PageCodeUtil.get(bookCategoryService.selectById(bookCategory));
    }
}
