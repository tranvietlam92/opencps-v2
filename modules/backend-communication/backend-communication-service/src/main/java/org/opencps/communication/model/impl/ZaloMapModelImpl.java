/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.communication.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.communication.model.ZaloMap;
import org.opencps.communication.model.ZaloMapModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ZaloMap service. Represents a row in the &quot;opencps_zalomap&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ZaloMapModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ZaloMapImpl}.
 * </p>
 *
 * @author khoavd
 * @see ZaloMapImpl
 * @see ZaloMap
 * @see ZaloMapModel
 * @generated
 */
@ProviderType
public class ZaloMapModelImpl extends BaseModelImpl<ZaloMap>
	implements ZaloMapModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a zalo map model instance should use the {@link ZaloMap} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_zalomap";
	public static final Object[][] TABLE_COLUMNS = {
			{ "zaloMapId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "uId", Types.VARCHAR },
			{ "telNo", Types.VARCHAR },
			{ "oAId", Types.VARCHAR },
			{ "isFollowed", Types.INTEGER },
			{ "payload", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("zaloMapId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("uId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("telNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("oAId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("isFollowed", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("payload", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_zalomap (zaloMapId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,uId VARCHAR(75) null,telNo VARCHAR(75) null,oAId VARCHAR(75) null,isFollowed INTEGER,payload VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_zalomap";
	public static final String ORDER_BY_JPQL = " ORDER BY zaloMap.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_zalomap.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(backend.communication.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.communication.model.ZaloMap"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(backend.communication.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.communication.model.ZaloMap"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(backend.communication.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.communication.model.ZaloMap"),
			true);
	public static final long GROUPID_COLUMN_BITMASK = 1L;
	public static final long OAID_COLUMN_BITMASK = 2L;
	public static final long TELNO_COLUMN_BITMASK = 4L;
	public static final long UID_COLUMN_BITMASK = 8L;
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(backend.communication.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.communication.model.ZaloMap"));

	public ZaloMapModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _zaloMapId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setZaloMapId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zaloMapId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ZaloMap.class;
	}

	@Override
	public String getModelClassName() {
		return ZaloMap.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zaloMapId", getZaloMapId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("uId", getUId());
		attributes.put("telNo", getTelNo());
		attributes.put("oAId", getOAId());
		attributes.put("isFollowed", getIsFollowed());
		attributes.put("payload", getPayload());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zaloMapId = (Long)attributes.get("zaloMapId");

		if (zaloMapId != null) {
			setZaloMapId(zaloMapId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String uId = (String)attributes.get("uId");

		if (uId != null) {
			setUId(uId);
		}

		String telNo = (String)attributes.get("telNo");

		if (telNo != null) {
			setTelNo(telNo);
		}

		String oAId = (String)attributes.get("oAId");

		if (oAId != null) {
			setOAId(oAId);
		}

		Integer isFollowed = (Integer)attributes.get("isFollowed");

		if (isFollowed != null) {
			setIsFollowed(isFollowed);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}
	}

	@Override
	public long getZaloMapId() {
		return _zaloMapId;
	}

	@Override
	public void setZaloMapId(long zaloMapId) {
		_zaloMapId = zaloMapId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getUId() {
		if (_uId == null) {
			return "";
		}
		else {
			return _uId;
		}
	}

	@Override
	public void setUId(String uId) {
		_columnBitmask |= UID_COLUMN_BITMASK;

		if (_originalUId == null) {
			_originalUId = _uId;
		}

		_uId = uId;
	}

	public String getOriginalUId() {
		return GetterUtil.getString(_originalUId);
	}

	@Override
	public String getTelNo() {
		if (_telNo == null) {
			return "";
		}
		else {
			return _telNo;
		}
	}

	@Override
	public void setTelNo(String telNo) {
		_columnBitmask |= TELNO_COLUMN_BITMASK;

		if (_originalTelNo == null) {
			_originalTelNo = _telNo;
		}

		_telNo = telNo;
	}

	public String getOriginalTelNo() {
		return GetterUtil.getString(_originalTelNo);
	}

	@Override
	public String getOAId() {
		if (_oAId == null) {
			return "";
		}
		else {
			return _oAId;
		}
	}

	@Override
	public void setOAId(String oAId) {
		_columnBitmask |= OAID_COLUMN_BITMASK;

		if (_originalOAId == null) {
			_originalOAId = _oAId;
		}

		_oAId = oAId;
	}

	public String getOriginalOAId() {
		return GetterUtil.getString(_originalOAId);
	}

	@Override
	public int getIsFollowed() {
		return _isFollowed;
	}

	@Override
	public void setIsFollowed(int isFollowed) {
		_isFollowed = isFollowed;
	}

	@Override
	public String getPayload() {
		if (_payload == null) {
			return "";
		}
		else {
			return _payload;
		}
	}

	@Override
	public void setPayload(String payload) {
		_payload = payload;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ZaloMap.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ZaloMap toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ZaloMap)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ZaloMapImpl zaloMapImpl = new ZaloMapImpl();

		zaloMapImpl.setZaloMapId(getZaloMapId());
		zaloMapImpl.setGroupId(getGroupId());
		zaloMapImpl.setCompanyId(getCompanyId());
		zaloMapImpl.setUserId(getUserId());
		zaloMapImpl.setUserName(getUserName());
		zaloMapImpl.setCreateDate(getCreateDate());
		zaloMapImpl.setModifiedDate(getModifiedDate());
		zaloMapImpl.setUId(getUId());
		zaloMapImpl.setTelNo(getTelNo());
		zaloMapImpl.setOAId(getOAId());
		zaloMapImpl.setIsFollowed(getIsFollowed());
		zaloMapImpl.setPayload(getPayload());

		zaloMapImpl.resetOriginalValues();

		return zaloMapImpl;
	}

	@Override
	public int compareTo(ZaloMap zaloMap) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), zaloMap.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZaloMap)) {
			return false;
		}

		ZaloMap zaloMap = (ZaloMap)obj;

		long primaryKey = zaloMap.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ZaloMapModelImpl zaloMapModelImpl = this;

		zaloMapModelImpl._originalGroupId = zaloMapModelImpl._groupId;

		zaloMapModelImpl._setOriginalGroupId = false;

		zaloMapModelImpl._setModifiedDate = false;

		zaloMapModelImpl._originalUId = zaloMapModelImpl._uId;

		zaloMapModelImpl._originalTelNo = zaloMapModelImpl._telNo;

		zaloMapModelImpl._originalOAId = zaloMapModelImpl._oAId;

		zaloMapModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ZaloMap> toCacheModel() {
		ZaloMapCacheModel zaloMapCacheModel = new ZaloMapCacheModel();

		zaloMapCacheModel.zaloMapId = getZaloMapId();

		zaloMapCacheModel.groupId = getGroupId();

		zaloMapCacheModel.companyId = getCompanyId();

		zaloMapCacheModel.userId = getUserId();

		zaloMapCacheModel.userName = getUserName();

		String userName = zaloMapCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			zaloMapCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			zaloMapCacheModel.createDate = createDate.getTime();
		}
		else {
			zaloMapCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			zaloMapCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			zaloMapCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		zaloMapCacheModel.uId = getUId();

		String uId = zaloMapCacheModel.uId;

		if ((uId != null) && (uId.length() == 0)) {
			zaloMapCacheModel.uId = null;
		}

		zaloMapCacheModel.telNo = getTelNo();

		String telNo = zaloMapCacheModel.telNo;

		if ((telNo != null) && (telNo.length() == 0)) {
			zaloMapCacheModel.telNo = null;
		}

		zaloMapCacheModel.oAId = getOAId();

		String oAId = zaloMapCacheModel.oAId;

		if ((oAId != null) && (oAId.length() == 0)) {
			zaloMapCacheModel.oAId = null;
		}

		zaloMapCacheModel.isFollowed = getIsFollowed();

		zaloMapCacheModel.payload = getPayload();

		String payload = zaloMapCacheModel.payload;

		if ((payload != null) && (payload.length() == 0)) {
			zaloMapCacheModel.payload = null;
		}

		return zaloMapCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{zaloMapId=");
		sb.append(getZaloMapId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", uId=");
		sb.append(getUId());
		sb.append(", telNo=");
		sb.append(getTelNo());
		sb.append(", oAId=");
		sb.append(getOAId());
		sb.append(", isFollowed=");
		sb.append(getIsFollowed());
		sb.append(", payload=");
		sb.append(getPayload());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("org.opencps.communication.model.ZaloMap");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>zaloMapId</column-name><column-value><![CDATA[");
		sb.append(getZaloMapId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>uId</column-name><column-value><![CDATA[");
		sb.append(getUId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>telNo</column-name><column-value><![CDATA[");
		sb.append(getTelNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oAId</column-name><column-value><![CDATA[");
		sb.append(getOAId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isFollowed</column-name><column-value><![CDATA[");
		sb.append(getIsFollowed());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>payload</column-name><column-value><![CDATA[");
		sb.append(getPayload());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ZaloMap.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ZaloMap.class, ModelWrapper.class
		};
	private long _zaloMapId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _uId;
	private String _originalUId;
	private String _telNo;
	private String _originalTelNo;
	private String _oAId;
	private String _originalOAId;
	private int _isFollowed;
	private String _payload;
	private long _columnBitmask;
	private ZaloMap _escapedModel;
}