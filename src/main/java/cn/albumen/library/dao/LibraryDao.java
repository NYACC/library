package cn.albumen.library.dao;

import cn.albumen.library.bean.Library;
import cn.albumen.library.dto.LibraryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Albumen
 */
@Repository
public interface LibraryDao {
    /**
     * 通过图书馆ID查找
     *
     * @param library
     * @return
     */
    LibraryDto selectById(Library library);

    /**
     * 通过馆长ID查找
     *
     * @param library
     * @return
     */
    List<LibraryDto> selectByCurator(Library library);

    /**
     * 有限制地获取图书馆
     *
     * @param library 条数
     * @return
     */
    List<LibraryDto> selectLimit(Library library);

    /**
     * 添加图书馆
     *
     * @param library
     * @return
     */
    int add(Library library);

    /**
     * 删除图书馆
     *
     * @param library
     * @return
     */
    int delete(Library library);

    /**
     * 修改图书馆
     *
     * @param library
     * @return
     */
    int update(Library library);

    /**
     * 获取图书馆数量
     *
     * @return
     */
    int count();
}
