package cn.albumen.library.controller;

import cn.albumen.library.annotation.ControllerLog;
import cn.albumen.library.bean.BookDetail;
import cn.albumen.library.constant.LevelConst;
import cn.albumen.library.constant.PageCodeEnum;
import cn.albumen.library.dto.BookSearchDto;
import cn.albumen.library.dto.PageCodeDto;
import cn.albumen.library.service.BookDetailService;
import cn.albumen.library.util.PageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @ControllerLog(description = "AddBookDetail",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public PageCodeDto add(@RequestBody BookDetail bookDetail) {
        return PageCodeUtil.add(bookDetailService.add(bookDetail));
    }

    @ControllerLog(description = "UpdateBookDetail",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public PageCodeDto update(@RequestBody BookDetail bookDetail) {
        return PageCodeUtil.update(bookDetailService.update(bookDetail));
    }

    @ControllerLog(description = "DeleteBookDetail",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public PageCodeDto delete(BookDetail bookDetail) {
        return PageCodeUtil.delete(bookDetailService.delete(bookDetail));
    }

    @ControllerLog(description = "RentBook",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/rent",method = RequestMethod.GET)
    public PageCodeDto rent(BookDetail bookDetail) {
        boolean flag = bookDetailService.updateRentBook(bookDetail);
        if(flag) {
            return new PageCodeDto(PageCodeEnum.RENT_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.RENT_FAILED);
        }
    }

    @ControllerLog(description = "BackBook",level = LevelConst.CRITICAL)
    @RequestMapping(path = "/back",method = RequestMethod.GET)
    public PageCodeDto back(BookDetail bookDetail) {
        boolean flag = bookDetailService.updateBackBook(bookDetail);
        if(flag) {
            return new PageCodeDto(PageCodeEnum.BACK_SUCCESS);
        }
        else {
            return new PageCodeDto(PageCodeEnum.BACK_FAILED);
        }
    }

    @ControllerLog(description = "SelectBookDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectbyid",method = RequestMethod.GET)
    public PageCodeDto selectById(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectById(bookDetail));
    }

    @ControllerLog(description = "SelectBookDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectrent",method = RequestMethod.GET)
    public PageCodeDto selectRent(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectRent(bookDetail));
    }

    @ControllerLog(description = "SelectBookDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectunrent",method = RequestMethod.GET)
    public PageCodeDto selectUnRent(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectUnRent(bookDetail));
    }

    @ControllerLog(description = "SelectBookDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectlike",method = RequestMethod.GET)
    public PageCodeDto selectLike(BookSearchDto bookSearchDto) {
        return PageCodeUtil.get(bookDetailService.selectLike(bookSearchDto));
    }

    @ControllerLog(description = "SelectBookDetail",level = LevelConst.INFO)
    @RequestMapping(path = "/selectall",method = RequestMethod.GET)
    public PageCodeDto selectAll(BookDetail bookDetail) {
        return PageCodeUtil.get(bookDetailService.selectAll(bookDetail));
    }
}
