package me.zhengjie.modules.mnt.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Data
public class ServerDeployQueryCriteria{

	/**
	 * 模糊
	 */
	@Query(type = Query.Type.INNER_LIKE)
    private String id;

}
