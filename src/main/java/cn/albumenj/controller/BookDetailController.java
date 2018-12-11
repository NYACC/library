package cn.albumenj.controller;

import cn.albumenj.bean.BookDetail;
import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.BookSearchDto;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.service.BookDetailService;
import cn.albumenj.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * @author Albumen
 */
@RestController
@RequestMapping("/bookdetail")
public class BookDetailController {
    @Autowired
    BookDetailService bookDetailService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody BookDetail bookDetail) {
        return PageCodeUtil.add(bookDetailService.add(bookDetail));
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody BookDetail bookDetail) {
        return PageCodeUtil.update(bookDetailService.update(bookDetail));
    }

    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public PageCodeDto delete(BookDetail bookDetail) {
        return PageCodeUtil.delete(bookDetailService.delete(bookDetail));
    }

    @RequestMapping(path = "/rent",method = RequestMethod.GET)
    public PageCodeDto rent(BookDetail bookDetail) {
        boolean flag = bookDetailService.rentBook(bookDetail);
        if(flag) {
            return new PageCodeDto(PageCodeEnum.RENT_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.RENT_FAILED);
        }
    }

    @RequestMapping(path = "/back",method = RequestMethod.GET)
    public PageCodeDto back(BookDetail bookDetail) {
        boolean flag = bookDetailService.backBook(bookDetail);
        if(flag) {
            return new PageCodeDto(PageCodeEnum.BACK_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.BACK_FAILED);
        }
    }

    @RequestMapping(path = "/selectbyid",method = RequestMethod.GET)
    public PageCodeDto selectById(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectById(bookDetail));
    }

    @RequestMapping(path = "/selectrent",method = RequestMethod.GET)
    public PageCodeDto selectRent(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectRent(bookDetail));
    }

    @RequestMapping(path = "/selectunrent",method = RequestMethod.GET)
    public PageCodeDto selectUnRent(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectUnRent(bookDetail));
    }

    @RequestMapping(path = "/selectlike",method = RequestMethod.GET)
    public PageCodeDto selectLike(BookSearchDto bookSearchDto) {
        return PageCodeUtil.get(bookDetailService.selectLike(bookSearchDto));
    }


    @RequestMapping(path = "/selectall",method = RequestMethod.GET)
    public PageCodeDto selectAll(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectAll(bookDetail));
    }
}
