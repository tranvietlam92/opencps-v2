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

package org.opencps.datamgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictCollectionModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DictCollection service. Represents a row in the &quot;opencps_dictcollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DictCollectionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DictCollectionImpl}.
 * </p>
 *
 * @author khoavu
 * @see DictCollectionImpl
 * @see DictCollection
 * @see DictCollectionModel
 * @generated
 */
@ProviderType
public class DictCollectionModelImpl extends BaseModelImpl<DictCollection>
	implements DictCollectionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dict collection model instance should use the {@link DictCollection} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_dictcollection";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "dictCollectionId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "collectionCode", Types.VARCHAR },
			{ "collectionName", Types.VARCHAR },
			{ "collectionNameEN", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "dataForm", Types.VARCHAR },
			{ "status", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dictCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("collectionCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("collectionNameEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dataForm", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_dictcollection (uuid_ VARCHAR(75) null,dictCollectionId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,collectionCode VARCHAR(75) null,collectionName VARCHAR(75) null,collectionNameEN VARCHAR(75) null,description VARCHAR(75) null,dataForm VARCHAR(75) null,status INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table opencps_dictcollection";
	public static final String ORDER_BY_JPQL = " ORDER BY dictCollection.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_dictcollection.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.datamgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.datamgt.model.DictCollection"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.datamgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.datamgt.model.DictCollection"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.datamgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.datamgt.model.DictCollection"),
			true);
	public static final long COLLECTIONCODE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long CREATEDATE_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.datamgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.datamgt.model.DictCollection"));

	public DictCollectionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _dictCollectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDictCollectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _dictCollectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DictCollection.class;
	}

	@Override
	public String getModelClassName() {
		return DictCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("dictCollectionId", getDictCollectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("collectionCode", getCollectionCode());
		attributes.put("collectionName", getCollectionName());
		attributes.put("collectionNameEN", getCollectionNameEN());
		attributes.put("description", getDescription());
		attributes.put("dataForm", getDataForm());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long dictCollectionId = (Long)attributes.get("dictCollectionId");

		if (dictCollectionId != null) {
			setDictCollectionId(dictCollectionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String collectionCode = (String)attributes.get("collectionCode");

		if (collectionCode != null) {
			setCollectionCode(collectionCode);
		}

		String collectionName = (String)attributes.get("collectionName");

		if (collectionName != null) {
			setCollectionName(collectionName);
		}

		String collectionNameEN = (String)attributes.get("collectionNameEN");

		if (collectionNameEN != null) {
			setCollectionNameEN(collectionNameEN);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String dataForm = (String)attributes.get("dataForm");

		if (dataForm != null) {
			setDataForm(dataForm);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getDictCollectionId() {
		return _dictCollectionId;
	}

	@Override
	public void setDictCollectionId(long dictCollectionId) {
		_dictCollectionId = dictCollectionId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
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

		_columnBitmask |= MODIFIEDDATE_COLUMN_BITMASK;

		if (_originalModifiedDate == null) {
			_originalModifiedDate = _modifiedDate;
		}

		_modifiedDate = modifiedDate;
	}

	public Date getOriginalModifiedDate() {
		return _originalModifiedDate;
	}

	@Override
	public String getCollectionCode() {
		if (_collectionCode == null) {
			return "";
		}
		else {
			return _collectionCode;
		}
	}

	@Override
	public void setCollectionCode(String collectionCode) {
		_columnBitmask |= COLLECTIONCODE_COLUMN_BITMASK;

		if (_originalCollectionCode == null) {
			_originalCollectionCode = _collectionCode;
		}

		_collectionCode = collectionCode;
	}

	public String getOriginalCollectionCode() {
		return GetterUtil.getString(_originalCollectionCode);
	}

	@Override
	public String getCollectionName() {
		if (_collectionName == null) {
			return "";
		}
		else {
			return _collectionName;
		}
	}

	@Override
	public void setCollectionName(String collectionName) {
		_collectionName = collectionName;
	}

	@Override
	public String getCollectionNameEN() {
		if (_collectionNameEN == null) {
			return "";
		}
		else {
			return _collectionNameEN;
		}
	}

	@Override
	public void setCollectionNameEN(String collectionNameEN) {
		_collectionNameEN = collectionNameEN;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public String getDataForm() {
		if (_dataForm == null) {
			return "";
		}
		else {
			return _dataForm;
		}
	}

	@Override
	public void setDataForm(String dataForm) {
		_dataForm = dataForm;
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				DictCollection.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			DictCollection.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DictCollection toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DictCollection)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DictCollectionImpl dictCollectionImpl = new DictCollectionImpl();

		dictCollectionImpl.setUuid(getUuid());
		dictCollectionImpl.setDictCollectionId(getDictCollectionId());
		dictCollectionImpl.setCompanyId(getCompanyId());
		dictCollectionImpl.setGroupId(getGroupId());
		dictCollectionImpl.setUserId(getUserId());
		dictCollectionImpl.setUserName(getUserName());
		dictCollectionImpl.setCreateDate(getCreateDate());
		dictCollectionImpl.setModifiedDate(getModifiedDate());
		dictCollectionImpl.setCollectionCode(getCollectionCode());
		dictCollectionImpl.setCollectionName(getCollectionName());
		dictCollectionImpl.setCollectionNameEN(getCollectionNameEN());
		dictCollectionImpl.setDescription(getDescription());
		dictCollectionImpl.setDataForm(getDataForm());
		dictCollectionImpl.setStatus(getStatus());

		dictCollectionImpl.resetOriginalValues();

		return dictCollectionImpl;
	}

	@Override
	public int compareTo(DictCollection dictCollection) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				dictCollection.getCreateDate());

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

		if (!(obj instanceof DictCollection)) {
			return false;
		}

		DictCollection dictCollection = (DictCollection)obj;

		long primaryKey = dictCollection.getPrimaryKey();

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
		DictCollectionModelImpl dictCollectionModelImpl = this;

		dictCollectionModelImpl._originalUuid = dictCollectionModelImpl._uuid;

		dictCollectionModelImpl._originalCompanyId = dictCollectionModelImpl._companyId;

		dictCollectionModelImpl._setOriginalCompanyId = false;

		dictCollectionModelImpl._originalGroupId = dictCollectionModelImpl._groupId;

		dictCollectionModelImpl._setOriginalGroupId = false;

		dictCollectionModelImpl._originalModifiedDate = dictCollectionModelImpl._modifiedDate;

		dictCollectionModelImpl._setModifiedDate = false;

		dictCollectionModelImpl._originalCollectionCode = dictCollectionModelImpl._collectionCode;

		dictCollectionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DictCollection> toCacheModel() {
		DictCollectionCacheModel dictCollectionCacheModel = new DictCollectionCacheModel();

		dictCollectionCacheModel.uuid = getUuid();

		String uuid = dictCollectionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			dictCollectionCacheModel.uuid = null;
		}

		dictCollectionCacheModel.dictCollectionId = getDictCollectionId();

		dictCollectionCacheModel.companyId = getCompanyId();

		dictCollectionCacheModel.groupId = getGroupId();

		dictCollectionCacheModel.userId = getUserId();

		dictCollectionCacheModel.userName = getUserName();

		String userName = dictCollectionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			dictCollectionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			dictCollectionCacheModel.createDate = createDate.getTime();
		}
		else {
			dictCollectionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			dictCollectionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			dictCollectionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		dictCollectionCacheModel.collectionCode = getCollectionCode();

		String collectionCode = dictCollectionCacheModel.collectionCode;

		if ((collectionCode != null) && (collectionCode.length() == 0)) {
			dictCollectionCacheModel.collectionCode = null;
		}

		dictCollectionCacheModel.collectionName = getCollectionName();

		String collectionName = dictCollectionCacheModel.collectionName;

		if ((collectionName != null) && (collectionName.length() == 0)) {
			dictCollectionCacheModel.collectionName = null;
		}

		dictCollectionCacheModel.collectionNameEN = getCollectionNameEN();

		String collectionNameEN = dictCollectionCacheModel.collectionNameEN;

		if ((collectionNameEN != null) && (collectionNameEN.length() == 0)) {
			dictCollectionCacheModel.collectionNameEN = null;
		}

		dictCollectionCacheModel.description = getDescription();

		String description = dictCollectionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			dictCollectionCacheModel.description = null;
		}

		dictCollectionCacheModel.dataForm = getDataForm();

		String dataForm = dictCollectionCacheModel.dataForm;

		if ((dataForm != null) && (dataForm.length() == 0)) {
			dictCollectionCacheModel.dataForm = null;
		}

		dictCollectionCacheModel.status = getStatus();

		return dictCollectionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", dictCollectionId=");
		sb.append(getDictCollectionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", collectionCode=");
		sb.append(getCollectionCode());
		sb.append(", collectionName=");
		sb.append(getCollectionName());
		sb.append(", collectionNameEN=");
		sb.append(getCollectionNameEN());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", dataForm=");
		sb.append(getDataForm());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("org.opencps.datamgt.model.DictCollection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dictCollectionId</column-name><column-value><![CDATA[");
		sb.append(getDictCollectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>collectionCode</column-name><column-value><![CDATA[");
		sb.append(getCollectionCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>collectionName</column-name><column-value><![CDATA[");
		sb.append(getCollectionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>collectionNameEN</column-name><column-value><![CDATA[");
		sb.append(getCollectionNameEN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dataForm</column-name><column-value><![CDATA[");
		sb.append(getDataForm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DictCollection.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DictCollection.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _dictCollectionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _originalModifiedDate;
	private boolean _setModifiedDate;
	private String _collectionCode;
	private String _originalCollectionCode;
	private String _collectionName;
	private String _collectionNameEN;
	private String _description;
	private String _dataForm;
	private int _status;
	private long _columnBitmask;
	private DictCollection _escapedModel;
}