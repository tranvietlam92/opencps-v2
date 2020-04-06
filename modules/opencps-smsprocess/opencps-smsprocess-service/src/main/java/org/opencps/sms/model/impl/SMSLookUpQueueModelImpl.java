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

package org.opencps.sms.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
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

import org.opencps.sms.model.SMSLookUpQueue;
import org.opencps.sms.model.SMSLookUpQueueModel;
import org.opencps.sms.model.SMSLookUpQueueSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the SMSLookUpQueue service. Represents a row in the &quot;opencps_smsqueue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SMSLookUpQueueModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SMSLookUpQueueImpl}.
 * </p>
 *
 * @author khoa
 * @see SMSLookUpQueueImpl
 * @see SMSLookUpQueue
 * @see SMSLookUpQueueModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SMSLookUpQueueModelImpl extends BaseModelImpl<SMSLookUpQueue>
	implements SMSLookUpQueueModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sms look up queue model instance should use the {@link SMSLookUpQueue} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_smsqueue";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "queueId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "moid", Types.VARCHAR },
			{ "src", Types.VARCHAR },
			{ "dest", Types.VARCHAR },
			{ "moseq", Types.VARCHAR },
			{ "cmdcode", Types.VARCHAR },
			{ "msgbody", Types.VARCHAR },
			{ "password_", Types.VARCHAR },
			{ "status", Types.INTEGER },
			{ "receivedDate", Types.TIMESTAMP },
			{ "userName", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("queueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("moid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("src", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("dest", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("moseq", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("cmdcode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("msgbody", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("password_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("receivedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_smsqueue (uuid_ VARCHAR(75) null,queueId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,moid VARCHAR(255) null,src VARCHAR(255) null,dest VARCHAR(255) null,moseq VARCHAR(255) null,cmdcode VARCHAR(255) null,msgbody VARCHAR(255) null,password_ VARCHAR(255) null,status INTEGER,receivedDate DATE null,userName VARCHAR(255) null)";
	public static final String TABLE_SQL_DROP = "drop table opencps_smsqueue";
	public static final String ORDER_BY_JPQL = " ORDER BY smsLookUpQueue.receivedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_smsqueue.receivedDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.sms.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.sms.model.SMSLookUpQueue"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.sms.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.sms.model.SMSLookUpQueue"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.sms.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.sms.model.SMSLookUpQueue"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long MOID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long RECEIVEDDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SMSLookUpQueue toModel(SMSLookUpQueueSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SMSLookUpQueue model = new SMSLookUpQueueImpl();

		model.setUuid(soapModel.getUuid());
		model.setQueueId(soapModel.getQueueId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setMoid(soapModel.getMoid());
		model.setSrc(soapModel.getSrc());
		model.setDest(soapModel.getDest());
		model.setMoseq(soapModel.getMoseq());
		model.setCmdcode(soapModel.getCmdcode());
		model.setMsgbody(soapModel.getMsgbody());
		model.setPassword(soapModel.getPassword());
		model.setStatus(soapModel.getStatus());
		model.setReceivedDate(soapModel.getReceivedDate());
		model.setUserName(soapModel.getUserName());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SMSLookUpQueue> toModels(SMSLookUpQueueSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SMSLookUpQueue> models = new ArrayList<SMSLookUpQueue>(soapModels.length);

		for (SMSLookUpQueueSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.sms.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.sms.model.SMSLookUpQueue"));

	public SMSLookUpQueueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _queueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setQueueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _queueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SMSLookUpQueue.class;
	}

	@Override
	public String getModelClassName() {
		return SMSLookUpQueue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("queueId", getQueueId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("moid", getMoid());
		attributes.put("src", getSrc());
		attributes.put("dest", getDest());
		attributes.put("moseq", getMoseq());
		attributes.put("cmdcode", getCmdcode());
		attributes.put("msgbody", getMsgbody());
		attributes.put("password", getPassword());
		attributes.put("status", getStatus());
		attributes.put("receivedDate", getReceivedDate());
		attributes.put("userName", getUserName());

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

		Long queueId = (Long)attributes.get("queueId");

		if (queueId != null) {
			setQueueId(queueId);
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

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String moid = (String)attributes.get("moid");

		if (moid != null) {
			setMoid(moid);
		}

		String src = (String)attributes.get("src");

		if (src != null) {
			setSrc(src);
		}

		String dest = (String)attributes.get("dest");

		if (dest != null) {
			setDest(dest);
		}

		String moseq = (String)attributes.get("moseq");

		if (moseq != null) {
			setMoseq(moseq);
		}

		String cmdcode = (String)attributes.get("cmdcode");

		if (cmdcode != null) {
			setCmdcode(cmdcode);
		}

		String msgbody = (String)attributes.get("msgbody");

		if (msgbody != null) {
			setMsgbody(msgbody);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date receivedDate = (Date)attributes.get("receivedDate");

		if (receivedDate != null) {
			setReceivedDate(receivedDate);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}
	}

	@JSON
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

	@JSON
	@Override
	public long getQueueId() {
		return _queueId;
	}

	@Override
	public void setQueueId(long queueId) {
		_queueId = queueId;
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
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

	@JSON
	@Override
	public String getMoid() {
		if (_moid == null) {
			return "";
		}
		else {
			return _moid;
		}
	}

	@Override
	public void setMoid(String moid) {
		_columnBitmask |= MOID_COLUMN_BITMASK;

		if (_originalMoid == null) {
			_originalMoid = _moid;
		}

		_moid = moid;
	}

	public String getOriginalMoid() {
		return GetterUtil.getString(_originalMoid);
	}

	@JSON
	@Override
	public String getSrc() {
		if (_src == null) {
			return "";
		}
		else {
			return _src;
		}
	}

	@Override
	public void setSrc(String src) {
		_src = src;
	}

	@JSON
	@Override
	public String getDest() {
		if (_dest == null) {
			return "";
		}
		else {
			return _dest;
		}
	}

	@Override
	public void setDest(String dest) {
		_dest = dest;
	}

	@JSON
	@Override
	public String getMoseq() {
		if (_moseq == null) {
			return "";
		}
		else {
			return _moseq;
		}
	}

	@Override
	public void setMoseq(String moseq) {
		_moseq = moseq;
	}

	@JSON
	@Override
	public String getCmdcode() {
		if (_cmdcode == null) {
			return "";
		}
		else {
			return _cmdcode;
		}
	}

	@Override
	public void setCmdcode(String cmdcode) {
		_cmdcode = cmdcode;
	}

	@JSON
	@Override
	public String getMsgbody() {
		if (_msgbody == null) {
			return "";
		}
		else {
			return _msgbody;
		}
	}

	@Override
	public void setMsgbody(String msgbody) {
		_msgbody = msgbody;
	}

	@JSON
	@Override
	public String getPassword() {
		if (_password == null) {
			return "";
		}
		else {
			return _password;
		}
	}

	@Override
	public void setPassword(String password) {
		_password = password;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@JSON
	@Override
	public Date getReceivedDate() {
		return _receivedDate;
	}

	@Override
	public void setReceivedDate(Date receivedDate) {
		_columnBitmask = -1L;

		_receivedDate = receivedDate;
	}

	@JSON
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
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				SMSLookUpQueue.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SMSLookUpQueue.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SMSLookUpQueue toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SMSLookUpQueue)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SMSLookUpQueueImpl smsLookUpQueueImpl = new SMSLookUpQueueImpl();

		smsLookUpQueueImpl.setUuid(getUuid());
		smsLookUpQueueImpl.setQueueId(getQueueId());
		smsLookUpQueueImpl.setGroupId(getGroupId());
		smsLookUpQueueImpl.setCompanyId(getCompanyId());
		smsLookUpQueueImpl.setUserId(getUserId());
		smsLookUpQueueImpl.setCreateDate(getCreateDate());
		smsLookUpQueueImpl.setModifiedDate(getModifiedDate());
		smsLookUpQueueImpl.setMoid(getMoid());
		smsLookUpQueueImpl.setSrc(getSrc());
		smsLookUpQueueImpl.setDest(getDest());
		smsLookUpQueueImpl.setMoseq(getMoseq());
		smsLookUpQueueImpl.setCmdcode(getCmdcode());
		smsLookUpQueueImpl.setMsgbody(getMsgbody());
		smsLookUpQueueImpl.setPassword(getPassword());
		smsLookUpQueueImpl.setStatus(getStatus());
		smsLookUpQueueImpl.setReceivedDate(getReceivedDate());
		smsLookUpQueueImpl.setUserName(getUserName());

		smsLookUpQueueImpl.resetOriginalValues();

		return smsLookUpQueueImpl;
	}

	@Override
	public int compareTo(SMSLookUpQueue smsLookUpQueue) {
		int value = 0;

		value = DateUtil.compareTo(getReceivedDate(),
				smsLookUpQueue.getReceivedDate());

		value = value * -1;

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

		if (!(obj instanceof SMSLookUpQueue)) {
			return false;
		}

		SMSLookUpQueue smsLookUpQueue = (SMSLookUpQueue)obj;

		long primaryKey = smsLookUpQueue.getPrimaryKey();

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
		SMSLookUpQueueModelImpl smsLookUpQueueModelImpl = this;

		smsLookUpQueueModelImpl._originalUuid = smsLookUpQueueModelImpl._uuid;

		smsLookUpQueueModelImpl._originalGroupId = smsLookUpQueueModelImpl._groupId;

		smsLookUpQueueModelImpl._setOriginalGroupId = false;

		smsLookUpQueueModelImpl._originalCompanyId = smsLookUpQueueModelImpl._companyId;

		smsLookUpQueueModelImpl._setOriginalCompanyId = false;

		smsLookUpQueueModelImpl._setModifiedDate = false;

		smsLookUpQueueModelImpl._originalMoid = smsLookUpQueueModelImpl._moid;

		smsLookUpQueueModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SMSLookUpQueue> toCacheModel() {
		SMSLookUpQueueCacheModel smsLookUpQueueCacheModel = new SMSLookUpQueueCacheModel();

		smsLookUpQueueCacheModel.uuid = getUuid();

		String uuid = smsLookUpQueueCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			smsLookUpQueueCacheModel.uuid = null;
		}

		smsLookUpQueueCacheModel.queueId = getQueueId();

		smsLookUpQueueCacheModel.groupId = getGroupId();

		smsLookUpQueueCacheModel.companyId = getCompanyId();

		smsLookUpQueueCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			smsLookUpQueueCacheModel.createDate = createDate.getTime();
		}
		else {
			smsLookUpQueueCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			smsLookUpQueueCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			smsLookUpQueueCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		smsLookUpQueueCacheModel.moid = getMoid();

		String moid = smsLookUpQueueCacheModel.moid;

		if ((moid != null) && (moid.length() == 0)) {
			smsLookUpQueueCacheModel.moid = null;
		}

		smsLookUpQueueCacheModel.src = getSrc();

		String src = smsLookUpQueueCacheModel.src;

		if ((src != null) && (src.length() == 0)) {
			smsLookUpQueueCacheModel.src = null;
		}

		smsLookUpQueueCacheModel.dest = getDest();

		String dest = smsLookUpQueueCacheModel.dest;

		if ((dest != null) && (dest.length() == 0)) {
			smsLookUpQueueCacheModel.dest = null;
		}

		smsLookUpQueueCacheModel.moseq = getMoseq();

		String moseq = smsLookUpQueueCacheModel.moseq;

		if ((moseq != null) && (moseq.length() == 0)) {
			smsLookUpQueueCacheModel.moseq = null;
		}

		smsLookUpQueueCacheModel.cmdcode = getCmdcode();

		String cmdcode = smsLookUpQueueCacheModel.cmdcode;

		if ((cmdcode != null) && (cmdcode.length() == 0)) {
			smsLookUpQueueCacheModel.cmdcode = null;
		}

		smsLookUpQueueCacheModel.msgbody = getMsgbody();

		String msgbody = smsLookUpQueueCacheModel.msgbody;

		if ((msgbody != null) && (msgbody.length() == 0)) {
			smsLookUpQueueCacheModel.msgbody = null;
		}

		smsLookUpQueueCacheModel.password = getPassword();

		String password = smsLookUpQueueCacheModel.password;

		if ((password != null) && (password.length() == 0)) {
			smsLookUpQueueCacheModel.password = null;
		}

		smsLookUpQueueCacheModel.status = getStatus();

		Date receivedDate = getReceivedDate();

		if (receivedDate != null) {
			smsLookUpQueueCacheModel.receivedDate = receivedDate.getTime();
		}
		else {
			smsLookUpQueueCacheModel.receivedDate = Long.MIN_VALUE;
		}

		smsLookUpQueueCacheModel.userName = getUserName();

		String userName = smsLookUpQueueCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			smsLookUpQueueCacheModel.userName = null;
		}

		return smsLookUpQueueCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", queueId=");
		sb.append(getQueueId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", moid=");
		sb.append(getMoid());
		sb.append(", src=");
		sb.append(getSrc());
		sb.append(", dest=");
		sb.append(getDest());
		sb.append(", moseq=");
		sb.append(getMoseq());
		sb.append(", cmdcode=");
		sb.append(getCmdcode());
		sb.append(", msgbody=");
		sb.append(getMsgbody());
		sb.append(", password=");
		sb.append(getPassword());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", receivedDate=");
		sb.append(getReceivedDate());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("org.opencps.sms.model.SMSLookUpQueue");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>queueId</column-name><column-value><![CDATA[");
		sb.append(getQueueId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moid</column-name><column-value><![CDATA[");
		sb.append(getMoid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>src</column-name><column-value><![CDATA[");
		sb.append(getSrc());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dest</column-name><column-value><![CDATA[");
		sb.append(getDest());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moseq</column-name><column-value><![CDATA[");
		sb.append(getMoseq());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cmdcode</column-name><column-value><![CDATA[");
		sb.append(getCmdcode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>msgbody</column-name><column-value><![CDATA[");
		sb.append(getMsgbody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>password</column-name><column-value><![CDATA[");
		sb.append(getPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>receivedDate</column-name><column-value><![CDATA[");
		sb.append(getReceivedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SMSLookUpQueue.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SMSLookUpQueue.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _queueId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _moid;
	private String _originalMoid;
	private String _src;
	private String _dest;
	private String _moseq;
	private String _cmdcode;
	private String _msgbody;
	private String _password;
	private int _status;
	private Date _receivedDate;
	private String _userName;
	private long _columnBitmask;
	private SMSLookUpQueue _escapedModel;
}