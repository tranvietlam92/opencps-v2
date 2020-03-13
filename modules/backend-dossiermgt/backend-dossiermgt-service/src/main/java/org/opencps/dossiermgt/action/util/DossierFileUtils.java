package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class DossierFileUtils {
	
	static Log  _log = LogFactoryUtil.getLog(DossierFileUtils.class);

	public static final String EXTEND_FILE = ".txt";
	
	public  FileEntry uploadFileEntry(long userId, long groupId, String sourceFile, String rootFolderName,
			ServiceContext serviceContext) throws Exception {
		
		DLFolder dlFolder = getFolder(serviceContext.getUserId(), groupId, serviceContext, rootFolderName);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		String fileName = System.currentTimeMillis() + PwdGenerator.getPassword(5) + EXTEND_FILE;

		_log.info("**FILE**URL**" + serviceContext.getUserAgent());
		_log.info("**FILE**URL**" + serviceContext.getUserId());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(groupId, dlFolder.getFolderId(), fileName, EXTEND_FILE,
				fileName, "UPLOAD_FILE_FORM",
				StringPool.BLANK, FileUtil.getBytes(new ByteArrayInputStream(sourceFile.getBytes())), serviceContext);
		
		
		return fileEntry;
	}
	
	public  String getFileContent(long fileEntryId) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			URL url = new URL(getFileURL(fileEntryId));
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				System.out.println(line);
			}
			in.close();

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return stringBuilder.toString();
	}

	public static String getFileURL(long fileEntryId) {

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
	
	public DLFolder getFolder(long userId, long groupId, ServiceContext serviceContext, String rootFolderName)
			throws Exception {

		Calendar cal = Calendar.getInstance();
		
		

		_log.info("**FILE**URL**" + serviceContext.getUserAgent());
		_log.info("**FILE**URL**" + serviceContext.getUserId());
		User user =
				UserLocalServiceUtil.getUser(serviceContext.getUserId());
			PermissionChecker checker =
				PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			
		int curYear = cal.get(Calendar.YEAR);
		int curMonth = cal.get(Calendar.MONTH) + 1;

		DLFolder rootFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				rootFolderName);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		if (Validator.isNull(rootFolder)) {
			rootFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, 0, rootFolderName, "",
					false, serviceContext);
		}

		DLFolder yearFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, rootFolder.getFolderId(), curYear + "");

		if (Validator.isNull(yearFolder)) {
			yearFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, rootFolder.getFolderId(),
					curYear + "", "", false, serviceContext);

		}

		DLFolder monthFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, yearFolder.getFolderId(), curMonth + "");

		if (Validator.isNull(monthFolder)) {
			monthFolder = DLFolderLocalServiceUtil.addFolder(userId, groupId, groupId, true, yearFolder.getFolderId(),
					curMonth + "", "", false, serviceContext);

		}
		
		setFolderPermissions(monthFolder);

		return monthFolder;

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
}
