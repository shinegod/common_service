package com.fx.user.service;

import com.fx.user.model.EDoc;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

public interface IEDocService extends IValueObjectService<EDoc> {
	
	/**
	 * getByUid	根据Uid获取用户电子证件信息
	 * @param uid
	 * @return User
	 * @exception 
	*/
	public List<EDoc> getByUid(int uid);
	
	/**
	 * getByUidList	管理后台使用，根据用户UID批量获取电子证件信息
	 * @param uidList
	 * @return List<EDoc>
	 * @exception 
	*/
	public List<EDoc> getByUidList(List<Integer> uidList);

	public List<EDoc> getByUidAndType(Map map);

	/**
	 * 查询当前用户下面的所有证件信息
	 * @param userId
	 * @return
	 */
	public List<EDoc> findListByUserId(Integer userId);

    /**
     * 根据用户Id查询其他文件
     * @param userId
     * @return
     */
    public List<EDoc> findOtherFilesByUserId(int userId);

	/**
	 *
	 * @param userId
	 * @param edocTypeId
     * @return
     */
	public EDoc findEdocByUserIdAndTypeId(int userId, int edocTypeId);

	/**
	 *
	 * @param userId
	 * @param typeId
     */
	public int doDeleteOldByUserIdAndTypeId(int userId, int typeId);

	/**
	 *
	 * @param userId
	 * @param typeId
	 */
	public int doDeleteAllByUserIdAndTypeId(int userId, int typeId);

	/**
	 *
	 * @param userId
	 * @param typeId
	 */
	public int doUpdateByUserIdAndTypeId(int userId, int typeId);
	/**
	 *
	 * @param userId
	 * @param type
     * @return
     */
	public List<EDoc> findEdocsByUserIdAndType(int userId, int type);

	public int doUpdateCheckPassByFilePath(String filePath);

	public int doDeleteByFilePath(String filePath);
}