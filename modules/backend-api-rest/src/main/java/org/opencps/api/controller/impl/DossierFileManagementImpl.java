package org.opencps.api.controller.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.dossierfile.model.DossierFileCopyInputModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossierfile.model.DossierFileResultsModel;
import org.opencps.api.dossierfile.model.DossierFileSearchModel;
import org.opencps.api.dossierfile.model.DossierFileSearchResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class DossierFileManagementImpl implements DossierFileManagement {

	private static final Log _log = LogFactoryUtil.getLog(DossierFileManagementImpl.class);
	
	
	public static final String FORM_FILE = "form_file";

	private String getFileURL(long fileEntryId) {

		String fileURL = "";

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

			String portalURL = PortalUtil.getPortalURL(company.getVirtualHostname(),
					PortalUtil.getPortalServerPort(false), false);

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);

			fileURL = portalURL + "/c/document_library/get_file?uuid=" + dlFileEntry.getUuid() + "&groupId="
					+ dlFileEntry.getGroupId();

		} catch (PortalException e) {

			// _log.error(e);
		}

		return fileURL;
	}

	@Override
	public Response uploadFile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));


		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject error = JSONFactoryUtil.createJSONObject();

		DataHandler dataHandler = file.getDataHandler();

		try {
			user =
					UserLocalServiceUtil.getUser(serviceContext.getUserId());
				PermissionChecker checker =
					PermissionCheckerFactoryUtil.create(user);
				PermissionThreadLocal.setPermissionChecker(checker);
				
			InputStream inputStream = dataHandler.getInputStream();
			
			//String fileName = dataHandler.getName();
			String fileType = FileUtil.getExtension(dataHandler.getName());
			
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			
			//DLFolder dlFolder = getFolder(serviceContext.getUserId(), groupId, serviceContext);
			
			_log.info("#USER ID " + serviceContext.getUserId());
			_log.info("#GROUPID " + groupId);
			
			serviceContext.setScopeGroupId(groupId);
			
			FileEntry fileEntry = FileUploadUtils.uploadDossierFile(serviceContext.getUserId(), groupId, inputStream, System.currentTimeMillis() + StringPool.DASH + dataHandler.getName(), fileType, serviceContext);
			
/*			FileEntry fileEntry = DLAppServiceUtil.addFileEntry(groupId, dlFolder.getFolderId(),
					fileName, fileType, System.currentTimeMillis() + StringPool.DASH + dataHandler.getName(),
					"UPLOAD_FILE_FORM", StringPool.BLANK, FileUtil.getBytes(inputStream), serviceContext);
*/			
			result.put("fileUrl", getFileURL(fileEntry.getFileEntryId()));
			result.put("fileId", fileEntry.getFileEntryId());
			
			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {

			_log.error(e);

			error.put("message", e.toString());

			return Response.status(500).entity(error.toJSONString()).build();
		}

	}
	
	public void setFolderPermissions(DLFolder folder)
			throws Exception {

			ResourcePermission resourcePermission = null;
			final Role guestMemberRole = RoleLocalServiceUtil.getRole(
				folder.getCompanyId(), RoleConstants.GUEST);
			final Role siteMemberRole = RoleLocalServiceUtil.getRole(
				folder.getCompanyId(), RoleConstants.SITE_MEMBER);
			ResourceAction resourceAction =
				ResourceActionLocalServiceUtil.getResourceAction(
					DLFolder.class.getName(), ActionKeys.VIEW);
			String[] actionIdsGuest = new String[] {
				ActionKeys.VIEW, ActionKeys.ACCESS
			};
			String[] actionIds = new String[] {
				ActionKeys.VIEW, ActionKeys.ACCESS, ActionKeys.SUBSCRIBE,
				ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.ADD_SHORTCUT,
				ActionKeys.ADD_SUBFOLDER, ActionKeys.PERMISSIONS, ActionKeys.DELETE
			};
			try {
				ResourcePermissionLocalServiceUtil.setResourcePermissions(
					folder.getCompanyId(), DLFolder.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(folder.getPrimaryKey()),
					guestMemberRole.getRoleId(), actionIdsGuest);
				ResourcePermissionLocalServiceUtil.setResourcePermissions(
					folder.getCompanyId(), DLFolder.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(folder.getPrimaryKey()),
					siteMemberRole.getRoleId(), actionIds);
			}
			catch (NoSuchResourcePermissionException e) {
				resourcePermission =
					ResourcePermissionLocalServiceUtil.createResourcePermission(
						CounterLocalServiceUtil.increment());
				resourcePermission.setCompanyId(folder.getCompanyId());
				resourcePermission.setName(DLFolder.class.getName());
				resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
				resourcePermission.setPrimKey(
					String.valueOf(folder.getPrimaryKey()));
				resourcePermission.setRoleId(guestMemberRole.getRoleId());
				resourcePermission.setActionIds(resourceAction.getBitwiseValue());// (ActionKeys.VIEW);
				ResourcePermissionLocalServiceUtil.addResourcePermission(
					resourcePermission);
			}
		}
	
	public static final String FILE_FORM_UPLOAD = "FILES_FORM_UPLOAD_OPENCPS_STORE";

	public DLFolder getFolder(long userId, long groupId, ServiceContext serviceContext) throws Exception {

		Calendar cal = Calendar.getInstance();

		int curYear = cal.get(Calendar.YEAR);
		int curMonth = cal.get(Calendar.MONTH) + 1;
		
		User user =
				UserLocalServiceUtil.getUser(serviceContext.getUserId());
			PermissionChecker checker =
				PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);

		DLFolder rootFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				FILE_FORM_UPLOAD);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		if (Validator.isNull(rootFolder)) {
			rootFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, 0, FILE_FORM_UPLOAD, "",
					false, serviceContext);
		}
		
		setFolderPermissions(rootFolder);

		_log.info("rootFolder" + rootFolder.getName());
		_log.info("rootFolder" + rootFolder.getGroupId());

		DLFolder yearFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, rootFolder.getFolderId(), curYear + "");

		if (Validator.isNull(yearFolder)) {
			yearFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, rootFolder.getFolderId(),
					curYear + "", "", false, serviceContext);

		}
		
		setFolderPermissions(yearFolder);

		_log.info("YEAR_" + yearFolder.getName());

		DLFolder monthFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, yearFolder.getFolderId(), curMonth + "");

		if (Validator.isNull(monthFolder)) {
			monthFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, yearFolder.getFolderId(),
					curMonth + "", "", false, serviceContext);

		}
		_log.info("MONTH_" + monthFolder.getName());
		_log.info("MONTH_" + monthFolder.getFolderId());
		
		setFolderPermissions(monthFolder);

		return monthFolder;

	}

	@Override
	public Response deleteFile(HttpServletRequest request,HttpHeaders header,
			Company company,Locale locale,User user, long fileid) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONObject error = JSONFactoryUtil.createJSONObject();

		try {

			DLFileEntryLocalServiceUtil.deleteDLFileEntry(fileid);

			result.put("message", "just-removed-file-id-" + fileid);

			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {

			error.put("message", e.getLocalizedMessage());

			return Response.status(500).entity(error.toJSONString()).build();
		}

	}


	@Override
	public Response getDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String password) {

		DossierFileResultsModel results = new DossierFileResultsModel();

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(id);

			results.setTotal(dossierFiles.size());
			results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * getDossierFilesByDossierReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, String referenceUid) {
	 * DossierFileResultsModel results = new DossierFileResultsModel();
	 * 
	 * // TODO: check user is loged or password for access dossier file
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * referenceUid);
	 * 
	 * List<DossierFile> dossierFiles =
	 * DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.
	 * getDossierId());
	 * 
	 * results.setTotal(dossierFiles.size());
	 * results.getData().addAll(DossierFileUtils.mappingToDossierFileData(
	 * dossierFiles));
	 * 
	 * return Response.status(200).entity(results).build();
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName, String fileType,
			String isSync, String formData) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			if (Validator.isNull(referenceUid)) {
				referenceUid = UUID.randomUUID().toString();
			}

			DataHandler dataHandler = file.getDataHandler();

//			_log.info("dataHandle: "+dataHandler);
//			_log.info("dataHandle.getContentType: "+dataHandler.getContentType());
//			_log.info("sourceFileName: "+dataHandler.getName());
			DossierFileActions action = new DossierFileActionsImpl();
			
			
			_log.info("__Start add file at:" + new Date());

			DossierFile dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
					dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
					dataHandler.getInputStream(), fileType, isSync, serviceContext);
			
			_log.info("__End add file at:" + new Date());
			
			if(Validator.isNotNull(formData)) {
				dossierFile.setFormData(formData);
			}
			
			_log.info("__Start update dossier file at:" + new Date());

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			_log.info("__End update dossier file at:" + new Date());

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			_log.info("__End bind to dossierFile" + new Date());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * addDossierFileByDossierReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, Attachment file, String
	 * dosserReferenceUid, String referenceUid, String dossierTemplateNo, String
	 * dossierPartNo, String fileTemplateNo, String displayName) {
	 * 
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * referenceUid);
	 * 
	 * DataHandler dataHandler = file.getDataHandler();
	 * 
	 * DossierFileActions action = new DossierFileActionsImpl();
	 * 
	 * DossierFile dossierFile = action.addDossierFile(groupId,
	 * dossier.getDossierId(), referenceUid, dossierTemplateNo, dossierPartNo,
	 * fileTemplateNo, displayName, dataHandler.getName(), 0,
	 * dataHandler.getInputStream(), serviceContext);
	 * 
	 * DossierFileModel result =
	 * DossierFileUtils.mappingToDossierFileModel(dossierFile);
	 * 
	 * return Response.status(200).entity(result).build();
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	
	
	@Override
	public Response cloneDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierFileCopyInputModel input) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();
		
			DossierFile dossierFile = action.cloneDossierFile(groupId, id, input.getDossierFileId(),
					input.getDossierTemplateNo(), input.getDossierPartNo(), serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
		    _log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response downloadByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String password) {

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);
			
			// TODO download file with dossierFileID
			if (Validator.isNull(dossierFile) && Validator.isNumber(referenceUid)) {
				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(referenceUid));
			}

			if (dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());

//				_log.info("fileEntry.getFileName(): "+fileEntry.getFileName()+ "|fileEntry.getMimeType(): "+fileEntry.getMimeType());
				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
						true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
				responseBuilder.header("Content-Type", fileEntry.getMimeType());

				return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * downloadByDossierReferenceUid_ReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, String dossierReferenceUid, String
	 * referenceUid, String password) {
	 * 
	 * // TODO: check user is loged or password for access dossier file
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * dossierReferenceUid);
	 * 
	 * DossierFile dossierFile =
	 * DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.
	 * getDossierId(), referenceUid);
	 * 
	 * if(dossierFile.getFileEntryId() > 0) { FileEntry fileEntry =
	 * DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	 * 
	 * File file =
	 * DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
	 * fileEntry.getVersion(), true);
	 * 
	 * ResponseBuilder responseBuilder = Response.ok((Object) file);
	 * 
	 * responseBuilder.header("Content-Disposition", "attachment; filename=\"" +
	 * fileEntry.getFileName() + "\""); responseBuilder.header("Content-Type",
	 * fileEntry.getMimeType());
	 * 
	 * return responseBuilder.build(); } else { return
	 * Response.status(HttpURLConnection.HTTP_NO_CONTENT).build(); }
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	@Override
	public Response updateDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file) {

//		_log.info("START POST DOSSIER=====");
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DataHandler dataHandle = file.getDataHandler();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

//			_log.info("dataHandle: "+dataHandle);
//			_log.info("dataHandle.getContentType: "+dataHandle.getContentType());
//			_log.info("sourceFileName: "+dataHandle.getName());
			DossierFile dossierFile = action.updateDossierFile(groupId, id, referenceUid, dataHandle.getName(),
					dataHandle.getInputStream(), serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getFormDataByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);

			return Response.status(200).entity(dossierFile.getFormData()).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getFormScriptByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);

			return Response.status(200).entity(dossierFile.getFormScript()).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response updateDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.updateDossierFileFormData(groupId, id, referenceUid, formdata,
					serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
		}
	}

	@Override
	public Response deleteDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.deleteDossierFile(groupId, id, referenceUid, serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getDossierFiles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierFileSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileSearchResultsModel results = new DossierFileSearchResultsModel();

			DossierFileActions action = new DossierFileActionsImpl();

			JSONObject dossierFileJsonObject = action.getDossierFiles(groupId, query.getKeyword(), query.getTemplate(),
					query.getType(), query.isOwner(), query.isOriginal(), query.getStart(), query.getEnd(),
					query.getSort(), query.getOrder(), serviceContext);
			
			List<Document> documents = (List<Document>) dossierFileJsonObject.get("data");

			results.setTotal(dossierFileJsonObject.getInt("total"));
			
			results.getData().addAll(DossierFileUtils.mappingToDossierFileSearchResultsModel(documents));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response downloadByDossierId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		String pathName = "";
		String realPath = "";
		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();
		DossierFileActions action = new DossierFileActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			int partType = 2;
			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByD_DP(id, partType);

			if (dossierFiles != null && dossierFiles.size() > 0) {
			if (dossierFiles.size() > 0) {
				if (dossierFiles.get(0).getFileEntryId() > 0) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFiles.get(0).getFileEntryId());
	
					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
					realPath = file.getPath();
					pathName = file.getPath() + "_" + String.valueOf(id);
				}
			}
	//			int index = realPath.lastIndexOf("\\");
				int index = realPath.lastIndexOf("/");
				File d = null;
				if (index > 0) {
					d = new File(pathName.substring(0, index));
				}
				if (d != null) {
			for (File f : d.listFiles()) {
				if (f.getName().substring(f.getName().lastIndexOf(".") + 1).equals("zip")) {
					f.delete();
				}
				if (f.isDirectory()) {
					f.delete();
				}
	
			}
				}
	
			for (DossierFile dossierFile : dossierFiles) {
				if (dossierFile.getFileEntryId() > 0) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	
					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
	//					String fileName = pathName + "\\" + fileEntry.getFileName();
						String fileName = pathName + "/" + fileEntry.getFileName();
					File dir = new File(pathName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					action.copyFile(file.getPath(), fileName);
				}
			}
	
			File dirName = new File(pathName);
	//			action.zipDirectory(dirName,
	//					pathName.substring(0, index) + "\\" + pathName.substring(index + 1, pathName.length()) + ".zip");
			action.zipDirectory(dirName,
						pathName.substring(0, index) + "/" + pathName.substring(index + 1, pathName.length()) + ".zip");
			// TODO:
			// Nen danh sach dossierFiles thanh file zip sau day gui lai client
	
	//			File fi = new File(
	//					pathName.substring(0, index) + "\\" + pathName.substring(index + 1, pathName.length()) + ".zip");
			File fi = new File(
						pathName.substring(0, index) + "/" + pathName.substring(index + 1, pathName.length()) + ".zip");
	
				ResponseBuilder responseBuilder = Response.ok(fi);
			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fi.getName() + "\"");
			responseBuilder.header("Content-Type", "application/zip");
	
			return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity("No Content").build();
			}
		} catch (Exception e) {
			return processException(e);
		}
	}

	private Response processException(Exception e) {
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

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}

	@Override
	public Response resetformdataDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.resetDossierFileFormData(groupId, id, referenceUid, formdata,
					serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
		}
	}

	@Override
	public Response removeAllDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id,
			String fileTemplateNo) {
		
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			//Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			
			DossierFileActions action = new DossierFileActionsImpl();

			action.deleteAllDossierFile(groupId, id, fileTemplateNo, serviceContext);

			//DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put("status", "success");

			return Response.status(200).entity(JSONFactoryUtil.serialize(result)).build();

		} catch (Exception e) {
			_log.info("DOSSIER_LOG_"+e);
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put("status", "error");
			result.put("message", "error");

			return Response.status(500).entity(JSONFactoryUtil.serialize(result)).build();
		}
	}

	@Override
	public Response getDossierFileByDeliverableCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String deliverableCode) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("********START *********");
		try {
			if(!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions actions = new DossierFileActionsImpl();

			DossierFile dossierFile = null;
			if (Validator.isNotNull(deliverableCode)) {
				_log.info("********START GET DOSSIERFILE*********"+ new Date().getTime());
				dossierFile = actions.getDossierFileByDeliverableCode(groupId, deliverableCode);
				_log.info("********END GET DOSSIERFILE *********"+ new Date().getTime());
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			if (dossierFile != null) {
				results.put("dossierId", dossierFile.getDossierId());
				results.put("referenceUid", dossierFile.getReferenceUid());
			}

			_log.info("********END *********");
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
		}

	}

	@Override
	public Response getAllDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		DossierFileResultsModel results = new DossierFileResultsModel();

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(id);

			results.setTotal(dossierFiles.size());
			results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response getDossierFileByDossierId_FileTemplateNo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String fileTemplateNo) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.getDossierFileByFileTemplateNo(id, fileTemplateNo);
			
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

}
