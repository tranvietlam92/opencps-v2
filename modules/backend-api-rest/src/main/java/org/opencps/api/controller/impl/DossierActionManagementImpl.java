package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierActionManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierActionUtils;
import org.opencps.api.dossier.model.ListContacts;
import org.opencps.api.dossieraction.model.DossierActionNextActionResultsModel;
import org.opencps.api.dossieraction.model.DossierActionResultsModel;
import org.opencps.api.dossieraction.model.DossierActionSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DeliverableActions;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DeliverableActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepRoleLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierActionManagementImpl implements DossierActionManagement {

	Log _log = LogFactoryUtil.getLog(DossierActionManagementImpl.class);

	private boolean isSpecial(Dossier dossier) {
		boolean isSpecical = false;

		try {

			DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode("DOSSIER_STATUS",
					dossier.getGroupId());

			String dossierStatus = dossier.getDossierStatus();

			DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(dossierStatus,
					dictCollection.getDictCollectionId(), dossier.getGroupId());

			if (Validator.isNotNull(dictItem)) {
				String metaData = dictItem.getMetaData();

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(metaData);

				isSpecical = GetterUtil.getBoolean(jsonObject.get("specialStatus"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return isSpecical;
	}

	private boolean hasPermisson(Dossier dossier, long userId) {

		boolean hasPermission = false;

		try {
			long dossierActionId = dossier.getDossierActionId();

			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(userId);

			DossierAction dossierAction = DossierActionLocalServiceUtil.getDossierAction(dossierActionId);

			ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(dossierAction.getStepCode(),
					dossier.getGroupId(), dossierAction.getServiceProcessId());

			List<ProcessStepRole> processStepRoles = ProcessStepRoleLocalServiceUtil
					.findByP_S_ID(processStep.getProcessStepId());

			for (ProcessStepRole processStepRole : processStepRoles) {
				for (Role role : userRoles) {
					if (processStepRole.getRoleId() == role.getRoleId()) {

						hasPermission = true;

						break;
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return hasPermission;
	}

	@Override
	public Response getListActions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		DossierActionNextActionResultsModel result = new DossierActionNextActionResultsModel();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			long userId = user.getUserId();

			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);

			long dossierActionId = dossier.getDossierActionId();

			List<DossierActionUser> dossierActionUsers = DossierActionUserLocalServiceUtil.getListUser(dossierActionId);

			_log.info("userId___" + userId);
			_log.info("dossierActionId___" + dossierActionId);
			_log.info("dossierActionUsers___size()" + dossierActionUsers.size());

			boolean hasPermission = false;
			
			_log.info("isSpecial(dossier)__" + isSpecial(dossier));

			if (isSpecial(dossier)) {
				for (DossierActionUser actionUser : dossierActionUsers) {
					if (actionUser.getUserId() == userId && actionUser.getModerator() == 1) {
						hasPermission = true;
						
						break;
					}
				}
			} else {
				boolean isInUserAction = false;
				
				for (DossierActionUser actionUser : dossierActionUsers) {
					if (actionUser.getUserId() == userId && actionUser.getModerator() == 1) {
						isInUserAction = true;
						
						break;
					}
				}
				
				hasPermission = isInUserAction || hasPermisson(dossier, userId);
			}

			if (!hasPermission) {
				result.setTotal(0);

				return Response.status(200).entity(result).build();
			}

			String referenceUid = StringPool.BLANK;

			if (dossierId == 0) {
				referenceUid = id;
			}

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossierId));
			params.put(DossierTerm.REFERENCE_UID, String.valueOf(referenceUid));
			params.put(DossierActionTerm.ACTION_CODE, query.getActionCode());
			params.put(DossierActionTerm.AUTO, query.getAuto());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONArray jsonData = actions.getNextActions(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.length());
			result.getData().addAll(DossierActionUtils.mappingToDoListReadNextActions(jsonData));
			// result.getData()
			// .addAll(DossierActionUtils.mappingToDoListActions((List<ProcessAction>)
			// jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response getListActionsExecuted(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DossierActionSearchModel query, String id) {

		DossierActions actions = new DossierActionsImpl();
		DossierActionResultsModel result = new DossierActionResultsModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);

			JSONObject jsonData = null;

			jsonData = (JSONObject) actions.getDossierActions(dossierId, groupId, query.isOwner(), query.getStart(),
					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);
			List<Document> documents = (List<Document>) jsonData.get("data");
			result.setTotal(jsonData.getInt("total"));
			result.getData().addAll(DossierActionUtils.mappingToDoListReadActionExecuted(documents));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response getListContacts(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		// TODO Auto-generated method stub

		DossierActions actions = new DossierActionsImpl();
		List<ListContacts> listContacts = new ArrayList<ListContacts>();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			long dossierId = GetterUtil.getLong(id);
			String referenceUid = null;
			if (dossierId == 0) {
				referenceUid = id + "";
			}

			JSONObject jsonData = (JSONObject) actions.getContacts(groupId, dossierId, referenceUid);

			listContacts = DossierActionUtils.mappingToDoListContacts((List<Dossier>) jsonData.get("ListContacts"));

			return Response.status(200).entity(listContacts).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response getVisited(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getRollback(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getByDeliverableState(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, String state) {
		// TODO Add Deliverable Type
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.getDossierFilesByDossierId(id);
			// _log.info("dossier File: "+ dossierFileList.get);
			StringBuilder sb = new StringBuilder();
			String deliverableCode = StringPool.BLANK;
			if (dossierFileList != null && dossierFileList.size() > 0) {
				int length = dossierFileList.size();
				// _log.info("Size dossier File: "+ length);
				int ii = 0;
				for (int i = 0; i < length; i++) {
					DossierFile dossierFile = dossierFileList.get(i);
					deliverableCode = dossierFile.getDeliverableCode();
					// _log.info("deliverableCode: "+ deliverableCode);
					if (Validator.isNotNull(deliverableCode)) {
						// _log.info("deliverableCode Check: "+
						// deliverableCode);
						ii += 1;
						if (ii == 1) {
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						} else {
							sb.append(StringPool.COMMA);
							sb.append(StringPool.APOSTROPHE);
							sb.append(deliverableCode);
							sb.append(StringPool.APOSTROPHE);
						}
					}
				}
				// _log.info("Str Dossier Id: "+ sb.toString());
			}

			DeliverableActions action = new DeliverableActionsImpl();
			//
			//
			List<Deliverable> deliverableList = action.getDeliverableByState(sb.toString(), state);
			// _log.info("Str list deliverable: "+ deliverableList);
			JSONArray results = JSONFactoryUtil.createJSONArray();
			if (deliverableList != null && deliverableList.size() > 0) {
				// int lengthDeliver = deliverableList.size();
				// _log.info("Size list deliverable: "+ deliverableList.size());
				String formData = StringPool.BLANK;
				for (Deliverable deliverable : deliverableList) {
					JSONObject formDetail = JSONFactoryUtil.createJSONObject();
					formData = deliverable.getFormData();
					// _log.info("formData: "+ formData);
					try {
						JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
						formDetail.put("so_chung_chi", jsonData.get("so_chung_chi"));
						formDetail.put("nguoi_ky_cc", jsonData.get("nguoi_ky_cc"));
						formDetail.put("ngay_ky_cc", jsonData.get("ngay_ky_cc"));
						formDetail.put("ten_doanh_nghiep", jsonData.get("ten_doanh_nghiep"));
						formDetail.put("ma_so_doanh_nghiep", jsonData.get("ma_so_doanh_nghiep"));
						formDetail.put("ma_ho_so", jsonData.get("ma_ho_so"));
						formDetail.put("so_ho_so", jsonData.get("so_ho_so"));
						formDetail.put("ngay_tiep_nhan", jsonData.get("ngay_tiep_nhan"));
						formDetail.put("ngay_gui", jsonData.get("ngay_gui"));
						formDetail.put("loai_san_pham", jsonData.get("loai_san_pham"));
						formDetail.put("nhan_hieu", jsonData.get("nhan_hieu"));
						formDetail.put("ma_kieu_loai", jsonData.get("ma_kieu_loai"));
						formDetail.put("ten_thuong_mai", jsonData.get("ten_thuong_mai"));

						String strReport = String.valueOf(jsonData.get("bien_ban"));
						try {
							JSONObject jsonReportData = JSONFactoryUtil.createJSONObject(strReport);
							formDetail.put("bien_ban@hinh_thuc_cap_giay_text",
									jsonReportData.get("hinh_thuc_cap_giay_text"));
							formDetail.put("bien_ban@so_bien_ban", jsonReportData.get("so_bien_ban"));
							formDetail.put("bien_ban@dang_kiem_vien_chinh", jsonReportData.get("dang_kiem_vien_chinh"));
							results.put(formDetail);
						} catch (Exception e) {
							_log.info("================");
							// -log.error(e);
						}
					} catch (Exception e) {
						_log.info("================");
						// -log.error(e);
					}

				}
			}

			// _log.info("Result: "+ results);
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}

	}

}
