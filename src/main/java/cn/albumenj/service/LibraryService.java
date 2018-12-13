package cn.albumenj.service;

import cn.albumenj.bean.Library;
import cn.albumenj.dto.LibraryDto;

import java.util.List;

/**
 * @author Albumen
 */
public interface LibraryService {
    /**
     * 通过ID查找图书馆
     *
     * @param library
     * @return
     */
    LibraryDto selectById(Library library);

    /**
     * 通过馆长ID查找图书馆
     *
     * @param library
     * @return
     */
    List<LibraryDto> selectByCurator(Library library);

    /**
     * 有限制性查找图书馆
     *
     * @param library
     * @return
     */
    List<LibraryDto> selectLimit(Library library);

    /**
     * 获取图书馆数量
     *
     * @param library
     * @return
     */
    int selectCount(Library library);

    /**
     * 添加图书馆
     *
     * @param library
     * @return
     */
    boolean add(Library library);

    /**
     * 删除图书馆
     *
     * @param library
     * @return
     */
    boolean delete(Library library);

    /**
     * 修改图书馆
     *
     * @param library
     * @return
     */
    boolean update(Library library);
}
