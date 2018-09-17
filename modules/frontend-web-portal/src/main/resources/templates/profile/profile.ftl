<#if (Request)??>
<#include "init.ftl">
</#if>
<div id="frmDetailAccount" class="MT20">
	<div class="row account-info" >
		<div class="col-sm-2 col-xs-12">
			
			<#if userType?has_content && userType == "employee">
				<input type="file" id="avatar_file_profile" accept="image/*"  onchange="profile_changeAvatarFileEntry(this)" style="display: none;" />
				<img id="profile_avatar_thumbnail" src="/o/frontend.web.portal/images/default_avatar.png" class="img-responsive max-width-100 img-rounded" style="width: 100%; height: auto;">
				<div class="text-center"><a id="change_avatar_profile" data-pk="${(employee.employeeId)!}" href="#" class="text-light-gray">Thay đổi avatar</a></div>
				<p class="name text-bold text-center" data-bind="text:fullnameEmployee" id="">${(employee.fullName)!}</p>
				<div>Thư điện tử: <span class="text-bold" data-bind="text:emailEmployee" id="">${(employee.email)!}</span></div>
				<div>Ngày sinh: <span class="text-bold" data-bind="text:birthdateEmployee" id="">${(employee.birthdate)!}</span></div>
				<div>Số điện thoại: <span class="text-bold" data-bind="text:mobileEmployee" id="">${(employee.telNo)!}</span></div>
			<#elseif userType?has_content && userType == "applicant">
				<input type="file" id="avatar_file_profile" accept="image/*"  onchange="profile_changeAvatarFileEntry(this)" style="display: none;"/>
				<img id="profile_avatar_thumbnail" src="/o/frontend.web.portal/images/default_avatar.png" class="img-responsive max-width-100 img-rounded" style="width: 100%; height: auto;">
				<div class="text-center"><a id="change_avatar_profile" data-pk="${(applicantId)!}" href="#" class="text-light-gray">Thay đổi avatar</a></div>
				<p class="name text-bold text-center" data-bind="text:applicantName" id="profileName"></p>
				<#if applicantIdType == 'business' >
					<div>Mã số thuế: <span class="text-bold" data-bind="text:applicantIdNo" id="profileIdNo"></span></div>
				<#else>
					<div>Số CMND/Hộ chiếu: <span class="text-bold" data-bind="text:applicantIdNo" id="profileIdNo"></span></div>
				</#if>
				<div>Ngày cấp: <span class="text-bold" data-bind="text:applicantIdDate" id="profileDate"></span></div>
				<div>Thư điện tử: <span class="text-bold" data-bind="text:contactEmail" id="profileEmail"></span></div>
			</#if>

			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#tttk" role="tab" data-toggle="tab">Thông tin tài khoản</a></li>
				<li role="presentation"><a href="#dmk" role="tab" data-toggle="tab">Đổi mật khẩu</a></li>
			</ul>
		</div>
		<div class="col-sm-10 col-xs-12">
			<div class="tab-content box">
				
				<#if userType?has_content && userType == "employee">

					<#include "profile_admin.ftl">

				<#elseif userType?has_content && userType == "applicant">

					<#if applicantIdType?has_content && applicantIdType == "business">
						<div role="tabpanel" class="tab-pane active" id="tttk">
							<div class="row-header align-middle-lg">
								<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
								<span class="text-bold">Thông tin tài khoản</span>
							</div>
							<div class="row-parts-content PB15">
								<div class="row MB15">
									<div class="col-sm-2">
										<p> Tên công ty</p>
									</div>
									<div class="col-sm-7">
										<span id="applicantName" data-pk="1" data-toggle="#editApplicantName" data-original-title="Nhập tên công ty" tabindex="-1" class="" data-bind="text:applicantName"> <#-- ${api.applicant.applicantName} --> </span>
										<span class="pull-right">
											<a href="#" id="editApplicantName" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Địa chỉ tổ chức</p>
									</div>
									<div class="col-sm-7">
										<span id="address" data-pk="1" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address"> </span>
										<span class="pull-right">
											<a href="#" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Tỉnh/ Thành phố</p>
									</div>
									<div class="col-sm-7">
										<span id="city" data-pk="1" data-type="select" data-toggle="#editCity" data-original-title="Chọn thành phố" tabindex="-1" class="" data-bind="text:cityName"><#--  ${api.applicant.cityName} --> </span>
										<span class="pull-right">
											<a href="#" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Quận/ Huyện</p>
									</div>
									<div class="col-sm-7">
										<span id="district" data-pk="1" data-type="select" data-toggle="#editDistrict" data-original-title="Chọn quận/ huyện" tabindex="-1" class="" data-bind="text:districtName"><#-- ${api.applicant.districtName} --></span>
										<span class="pull-right">
											<a href="#" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Xã/ Phường</p>
									</div>
									<div class="col-sm-7">
										<span id="wards" data-pk="1" data-type="select" data-toggle="#editWards" data-original-title="Chọn xã/ phường" tabindex="-1" class="" data-bind="text:wardName"><#-- ${api.applicant.wardName} --></span>
										<span class="pull-right">
											<a href="#" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Điện thoại</p>
									</div>
									<div class="col-sm-7">
										<span id="phone" data-pk="1" data-toggle="#editPhone" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo"><#-- ${api.applicant.contactTelNo} --></span>
										<span class="pull-right">
											<a href="#" id="editPhone" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Thư điện tử - Email</p>
									</div>
									<div class="col-sm-7">
										<span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Nhập email" tabindex="-1" class="" data-bind="text:contactEmail"><#-- ${api.applicant.contactEmail} --> </span>
										<!-- <span class="pull-right">
											<a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
										</span> -->
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Tên người đại diện</p>
									</div>
									<div class="col-sm-7">
										<span id="contactName" data-pk="1" data-toggle="#editContactName" data-original-title="Tên người đại diện" tabindex="-1" class="" data-bind="text:contactName"><#-- ${api.applicant.contactName} --></span>
										<span class="pull-right">
											<a href="#" id="editContactName" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
							</div>
						</div>
					<#elseif applicantIdType?has_content && applicantIdType == "citizen">
						<div role="tabpanel" class="tab-pane active" id="tttk">
							<div class="row-header align-middle-lg">
								<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
								<span class="text-bold">Thông tin tài khoản</span>
							</div>
							<div class="row-parts-content PB15">
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Họ và tên</p>
									</div>
									<div class="col-sm-7">
										<span id="applicantName" data-pk="1" data-toggle="#editApplicantName" data-original-title="Nhập họ và tên" tabindex="-1" class="" data-bind="text:applicantName"><#-- ${api.applicant.applicantName} --></span>
										<span class="pull-right">
											<a href="#" id="editApplicantName" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Địa chỉ</p>
									</div>
									<div class="col-sm-7">
										<span id="address" data-pk="1" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address"><#-- ${api.applicant.address} --></span>
										<span class="pull-right">
											<a href="#" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Tỉnh/ Thành phố</p>
									</div>
									<div class="col-sm-7">
										<span id="city" data-pk="1" data-type="select" data-toggle="#editCity" data-original-title="Chọn tỉnh/ thành phố" tabindex="-1" class="" data-bind="text:cityName"><#-- ${api.applicant.cityName} --></span>
										<span class="pull-right">
											<a href="#" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Quận/ Huyện</p>
									</div>
									<div class="col-sm-7">
										<span id="district" data-pk="1" data-type="select" data-toggle="#editDistrict" data-original-title="Chọn quận/ huyện" tabindex="-1" class="" data-bind="text:districtName"><#-- ${api.applicant.districtName} --></span>
										<span class="pull-right">
											<a href="#" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Xã/ Phường</p>
									</div>
									<div class="col-sm-7">
										<span id="wards" data-pk="1" data-type="select" data-toggle="#editWards" data-original-title="Chọn xã/ phường" tabindex="-1" class="" data-bind="text:wardName"><#-- ${api.applicant.wardName} --></span>
										<span class="pull-right">
											<a href="#" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Điện thoại</p>
									</div>
									<div class="col-sm-7">
										<span id="phone" data-pk="1" data-toggle="#editPhone" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo"><#-- ${api.applicant.contactTelNo} --></span>
										<span class="pull-right">
											<a href="#" id="editPhone" style="float: right"><i class="fa fa-pencil"></i></a>
										</span>
									</div>
								</div>
								<div class="row MB15">
									<div class="col-sm-2">
										<p>Thư điện tử - Email</p>
									</div>
									<div class="col-sm-7">
										<span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Nhập email" tabindex="-1" class="" data-bind="text:contactEmail"><#-- ${api.applicant.contactEmail} --></span>
										<#-- <span class="pull-right">
											<a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
										</span> -->
									</div>
								</div>
							</div>

						</div>
					</#if>
				<#else>
				</#if>

				
				<#-- <#elseif employee?has_content>
				<div role="tabpanel" class="tab-pane active" id="tttk">
				
					<div class="row-header align-middle-lg">
						<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
						<span class="text-bold">Thông tin tài khoản</span>
					</div>
					<div class="row-parts-content PB15">
						<div class="row MB15">
							<div class="col-sm-2">
								<p>Họ và tên</p>
							</div>
							<div class="col-sm-7">
								<span id="fullname" data-pk="1" data-toggle="#editFullname" data-original-title="Nhập họ và tên" tabindex="-1" class="" >
									${employee.fullname}
								</span>
								<span class="pull-right">
									<a href="#" id="editFullname" style="float: right"><i class="fa fa-pencil"></i></a>
								</span>
							</div>
						</div>
						<div class="row MB15">
							<div class="col-sm-2">
								<p>Giới tính</p>
							</div>
							<div class="col-sm-7">
								<span id="gender" data-pk="1" data-type="select" data-toggle="#editGender" data-original-title="Chọn tỉnh/ thành phố" tabindex="-1" class="">${employee.gender} </span>
								<span class="pull-right">
									<a href="#" id="editGender" style="float: right"><i class="fa fa-pencil"></i></a>
								</span>
							</div>
						</div>
						<div class="row MB15">
							<div class="col-sm-2">
								<p>Điện thoại cơ quan</p>
							</div>
							<div class="col-sm-7">
								<span id="telNo" data-pk="1" data-toggle="#editTelNo" data-original-title="Nhập số điện thoại" tabindex="-1" class="" > ${employee.telNo} </span>
								<span class="pull-right">
									<a href="#" id="editTelNo" style="float: right"><i class="fa fa-pencil"></i></a>
								</span>
							</div>
						</div>
						<div class="row MB15">
							<div class="col-sm-2">
								<p>Điện thoại cá nhân</p>
							</div>
							<div class="col-sm-7">
								<span id="mobile" data-pk="1" data-toggle="#editMobile" data-original-title="Nhập số điện thoại" tabindex="-1" class="" > ${employee.mobile} </span>
								<span class="pull-right">
									<a href="#" id="editMobile" style="float: right"><i class="fa fa-pencil"></i></a>
								</span>
							</div>
						</div>
						<div class="row MB15">
							<div class="col-sm-2">
								<p>Thư điện tử - Email</p>
							</div>
							<div class="col-sm-7">
								<span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Nhập email" tabindex="-1" class="" >${employee.email} </span>
								<span class="pull-right">
									<a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
								</span>
							</div>
						</div>
					</div>
				
				</div>
				<#else> -->
				
				<div role="tabpanel" class="tab-pane" id="dmk">
					<div class="row-header align-middle-lg">
						<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
						<span class="text-bold">Đổi mật khẩu</span>
					</div>
					<form id="fmChangePasswordUser">
						<div class="row-parts-content PB15">
							<div class="row">
								<div class="col-sm-2">
									<label class="with-input-sm">Mật khẩu hiện tại:</label>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<input type="password" class="form-control" id="old_password" name="old_password" required="required" data-required-msg="Trường nhập yêu cầu bắt buộc">
										<span data-for='old_password' class='k-invalid-msg'>
									</div>
								</div>
								<div class="col-sm-6 MT5" id="messagePassword">

								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label class="with-input-sm">Mật khẩu mới:</label>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<input type="password" class="form-control" id="new_password" name="new_password" required="required" data-checkpass-field="new_password" data-checkpass-msg='Mật khẩu gồm ít nhất 8 ký tự, có ít nhất 1 ký tự hoa, 1 ký tự thường, 1 chữ số và 1 ký tự đặc biệt!' data-required-msg="Trường nhập yêu cầu bắt buộc">
										<span data-for='new_password' class='k-invalid-msg'>
									</div>
								</div>
								<div class="col-sm-6">
									
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label class="with-input-sm">Nhập lại mật khẩu mới:</label>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<input type="password" class="form-control" id="retype_new_password" name="retype_new_password" required="required" data-required-msg="Trường nhập yêu cầu bắt buộc" data-checkequal-field="retype_new_password" data-checkequal-msg='Nhập lại mật khẩu không đúng!'>
										<span data-for='retype_new_password' class='k-invalid-msg'>
									</div>
									<div class="">
										<input type="checkbox" id="show_password">
										 <label>Hiển thị mật khẩu</label>
									</div>
								</div>
								<div class="col-sm-6">
									
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">

								</div>
								<div class="col-sm-10">
									<button class="btn btn-active" type="button" id="btn-change-password-user" data-pk="${(userId)!}">Lưu thay đổi</button>
								</div>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		<span id="notification"></span>
	</div>
</div>

<script type="text/javascript">
	var resultRet = false;
	var notification = $("#notification").kendoNotification().data("kendoNotification");
	var validator;
	$(function ($) {
		//$('[data-toggle="tooltip"]').tooltip();
		validator = $("#fmChangePasswordUser");
		kendo.init(validator);
		validator.kendoValidator({
			rules: {
				checkpass: function (input) {
					if (input.is("[name=new_password]")) {
						var str = $("#new_password").val();
						var reg = /^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}$/;
						if (!reg.test(str)) {      
							return false;                              
						}
					}
					return true;
				},
				checkequal: function(input) {
					if (input.is("[name=retype_new_password]")) {
						var retype_new_password = $("#retype_new_password").val();
						var new_password = $("#new_password").val();
						if (retype_new_password !== new_password) {      
							return false;                              
						}
					}
					return true;
				}
			}
		});
	})
	
	$('#btn-change-password-user').click(function(){
		// var validator = $("#fmChangePasswordUser").kendoValidator().data("kendoValidator");
		var userId = $(this).attr("data-pk");
		var str = $("#new_password").val();
        var reg = /^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\w!@#$%^&*]{8,}$/;
		if (validator.data('kendoValidator').validate()) {	
			if ($("#new_password").val() != $("#retype_new_password").val()){
				notification.show({
							message: "Xác nhận mật khẩu mới không đúng!"
						}, "error");
			}else if(!reg.test(str)){
				notification.show({ message:"Mật khẩu gồm ít nhất 8 ký tự, có ít nhất 1 ký tự hoa, 1 ký tự thường, 1 chữ số và 1 ký tự đặc biệt" }, "error");
			}else {
				var type = "${(userType)!}";
				if (type === 'applicant') {
					$.ajax({
						url : "${api.server}/users/"+userId+"/changepass",
						dataType : "json",
						type : "POST",
						headers: {"groupId": ${groupId}},
						data : {
							oldPassword : $("#old_password").val(),
							newPassword : $("#retype_new_password").val()
						},
						success : function(result){
							if (result == 0) {
								notification.show({
									message: "Mật khẩu hiện tại không chính xác"
								}, "error");
							}else if(result == 1) {
								notification.show({
									message: "Có lỗi sảy ra, vui lòng thử lại"
								}, "error");
							}else {
								notification.show({
									message: "Yêu cầu được thực hiện thành công"
								}, "success");
								$("#messagePassword").html('');
							}
						},
						error : function(xhr){
							$("#messagePassword").html('<span class="red"><i class="fa fa-times" aria-hidden="true"></i> <span class="message">Mật khẩu hoặc tài khoản không đúng</span></span>');
						}
					});	
				} else if(type === "employee"){
					$.ajax({
						url : "${api.server}/users/"+userId+"/changepass/employee",
						dataType : "json",
						type : "POST",
						headers: {"groupId": ${groupId}},
						data : {
							oldPassword : $("#old_password").val(),
							newPassword : $("#retype_new_password").val()
						},
						success : function(result){
							if(result){
								notification.show({
									message: "Đổi mật khẩu thực hiện thành công"
								}, "success");
								$("#messagePassword").html('');
							}else{
								notification.show({
									message: "Mật khẩu hiện tại nhập không chính xác"
								}, "error");
								$("#messagePassword").html('');
							}
						},
						error : function(xhr){
							$("#messagePassword").html('<span class="red"><i class="fa fa-times" aria-hidden="true"></i> <span class="message">Mật khẩu hoặc tài khoản không đúng</span></span>');
						}
					});
				}
			}
		}
	});

	$("#show_password").click(function(){
		if ($("#show_password").is(":checked")){
			$("#old_password")[0]['type'] = 'input';
			$("#new_password")[0]['type'] = 'input';
			$("#retype_new_password")[0]['type'] = 'input';
		} else {
			$("#old_password")[0]['type'] = 'password';
			$("#new_password")[0]['type'] = 'password';
			$("#retype_new_password")[0]['type'] = 'password';
		}
	});
	
	/*var validator = $("#fm").kendoValidator().data("kendoValidator");

	$("form").submit(function(event) {
		event.preventDefault();
		if (validator.validate()) {
			if ($("#new_password").val().length < 6 || $("#retype_new_password").val().length < 6){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else if ($("#new_password").val() != $("#retype_new_password").val()){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else if (!checkOldPassword(11111, $("#old_password").val())){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else {
				changePassword($("#old_password").val(), $("#new_password").val(), $("#retype_new_password").val());
			}
		}
	});*/

	function checkOldPassword(userId, oldPassword){
		$.ajax({
			url: "${api.server}/users/"+userId+"/checkpass/"+oldPassword,
			type: "GET",
			dataType: "json",
			headers: {"groupId": ${groupId}},
			success: function(result) {
				resultRet = result;
			}
		});
	};

	function changePassword(oldPassword, newPassword, retypeNewPassword){
		$.ajax({
			url: "${api.server}" + "/changePassword",
			type: "POST",
			dataType: "json",
			data: {
				oldPassword: oldPassword,
				newPassword: newPassword,
				retypeNewPassword: retypeNewPassword
			},
			headers: {"groupId": ${groupId}},
			success: function(res) {
				notification.show({
					message: "Đổi mật khẩu thành công"
				}, "success");
			},
			error: function(res) {
				notification.show({
					message: "Xẩy ra lỗi, vui lòng thử lại"
				}, "error");
			}
		});
	}

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var updateProfileURL = '/o/rest/v2/applicants/${(applicant.applicantId)!}';
	var updateProfileEmployeeURL = '/o/rest/v2/applicants/${(employee.employeeId)!}';
	
	$('#applicantName').editable({
		url: updateProfileURL,
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		emptytext : "-",
		params: function(params) {
			return {
				applicantName: params.value
			};
		},
		validate: function(value) {
			if (value.length < 1){
			  return 'Đây là trường bắt buộc';
			}
		},
		success: function(response, newValue) {
			$("#profileName").html(newValue);
		},
		error: function(event, id, obj) {

		}
	});


	$('#address').editable({
		url: updateProfileURL,
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		emptytext : "-",
		params: function(params) {
			return {
				address: params.value
			};
		},
		validate: function(value) {
		  if (value.length < 1){
		    return 'Đây là trường bắt buộc';
		  }
		},
		success: function(data) {

		},
		error: function(event, id, obj) {

		}
	});

	$('#city').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var cityName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : 0
				},
				success : function(result){
					var items = result.data;
					if (items) {
						for (var i = 0; i < items.length; i++) {
							if (items[i].itemCode == params.value){
								cityName = items[i].itemName;
							}
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				cityCode: params.value,
				cityName: cityName
			};
		},
		validate: function(value) {
			if (value.length < 1){
			  return 'Đây là trường bắt buộc';
			}
		},
		success: function(response, newValue) {
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : newValue
				},
				success : function(result){
					var arrDataRes = result.data;
					if (arrDataRes) {
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					}
				},
				error : function(xhr){

				}
			});
			$('#district').editable('option', 'source', arrDisplay);
			$('#district').html("-");
			$('#wards').html("-");
			$.ajax({
				url : "${api.server}/applicants/${(applicant.applicantId)!}",
				dataType : "json",
				type : "PUT",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					districtCode: "",
					districtName: "",
					wardCode: "",
					wardName: "",
				},
				success : function(result){
					
				},
				error : function(xhr){

				}
			});
		},
		error: function(event, id, obj) {

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : 0
				},
				success : function(result){
					var arrDataRes = result.data;
					if (arrDataRes) {
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					}
					
				},
				error : function(xhr){

				}
			});
			return arrDisplay;
		}
	});


	$('#district').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var districtName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : $('#city').editable('getValue', true)
				},
				success : function(result){
					var items = result.data;
					if (items) {
						for (var i = 0; i < items.length; i++) {
							if (items[i].itemCode == params.value){
								districtName = items[i].itemName;
							}
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				districtCode: params.value,
				districtName: districtName
			};
		},
		validate: function(value) {
			if (value.length <= 1){
			  return 'Đây là trường bắt buộc';
			}
		},
		success : function(response, newValue){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : newValue
				},
				success : function(result){
					var arrDataRes = result.data;
					if (arrDataRes) {
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					}
				},
				error : function(xhr){

				}
			});
			$('#wards').editable('option', 'source', arrDisplay);
			$('#wards').html("-");
			$.ajax({
				url : "${api.server}/applicants/${(applicant.applicantId)!}",
				dataType : "json",
				type : "PUT",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					wardCode: "",
					wardName: "",
				},
				success : function(result){
					
				},
				error : function(xhr){

				}
			});
		},
		error : function(xhr){

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			if ("${(applicant.cityCode)!}" && "${(applicant.cityCode)!}" !== '-') {
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : "${(applicant.cityCode)!}"
					},
					success : function(result){
						var arrDataRes = result.data;
						if (arrDataRes) {
							for (var i = 0; i < arrDataRes.length; i++) {
								arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
							}
						}
					},
					error : function(xhr){

					}
				});
			}
			return arrDisplay;
		}
	});

	$('#wards').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var wardName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : $('#district').editable('getValue', true)
				},
				success : function(result){
					var items = result.data;
					for (var i = 0; i < items.length; i++) {
						if (items[i].itemCode == params.value){
							wardName = items[i].itemName;
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				wardCode: params.value,
				wardName: wardName
			};
		},
		validate: function(value) {
			if (value.length < 1){
			  return 'Đây là trường bắt buộc';
			}
		},
		success : function(data){

		},
		error : function(xhr){

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			if ("${(applicant.districtCode)!}" && "${(applicant.districtCode)!}" !== '-') {
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : "${(applicant.districtCode)!}"
					},
					success : function(result){
						var arrDataRes = result.data;
						if (arrDataRes) {
							for (var i = 0; i < arrDataRes.length; i++) {
								arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
							}
						}
					},
					error : function(xhr){

					}
				});
			}
			
			return arrDisplay;
		}
	});

	$('#phone').editable({
		url: updateProfileURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				contactTelNo: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(data) {
			
		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#email').editable({
		url: updateProfileURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				contactEmail: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileEmail").html(newValue);

		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});
	
	$('#represntative').editable({
		url: 'post.php',
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		success: function(data) {
			alert(data);
		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#gender').editable({
		url: updateProfileEmployeeURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				gender: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success : function(data){

		},
		error : function(xhr){

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			arrDisplay.push( { value : "Nam", text : "Nam"});
			arrDisplay.push( { value : "Nu", text : "Nữ"});
			return arrDisplay;
		}
	});

	$('#fullname').editable({
		url: updateProfileEmployeeURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				fullname: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileEmail").html(newValue);

		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#telNo').editable({
		url: updateProfileEmployeeURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				telNo: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileEmail").html(newValue);

		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#mobile').editable({
		url: updateProfileEmployeeURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				mobile: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileEmail").html(newValue);

		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#editFullname').click(function(e) {
		e.stopPropagation();
		$('#fullname').editable('toggle');
	});

	$('#editGender').click(function(e) {
		e.stopPropagation();
		$('#gender').editable('toggle');
	});

	$('#editMobile').click(function(e) {
		e.stopPropagation();
		$('#mobile').editable('toggle');
	});

	$('#editTelNo').click(function(e) {
		e.stopPropagation();
		$('#telNo').editable('toggle');
	});

	//================================================


	$('#editApplicantName').click(function(e) {
		e.stopPropagation();
		$('#applicantName').editable('toggle');
	});

	$('#editAddress').click(function(e) {
		e.stopPropagation();
		$('#address').editable('toggle');
	});

	$('#editCity').click(function(e) {
		e.stopPropagation();
		$('#city').editable('toggle');
	});

	$('#editDistrict').click(function(e) {
		e.stopPropagation();
		$('#district').editable('toggle');
	});

	$('#editWards').click(function(e) {
		e.stopPropagation();
		$('#wards').editable('toggle');
	});

	$('#editPhone').click(function(e) {
		e.stopPropagation();
		$('#phone').editable('toggle');
	});

	$('#editContactName').click(function(e) {
		e.stopPropagation();
		$('#contactName').editable('toggle');
	});

	$('#editEmail').click(function(e) {
		e.stopPropagation();
		$('#email').editable('toggle');
	});

	$('#editRepresntative').click(function(e) {
		e.stopPropagation();
		$('#represntative').editable('toggle');
	});

	var pullDetailProfile = function(id){
		if("${(userType)!}" === "applicant"){
			$.ajax({
				url : "${api.server}/applicants/${(applicant.applicantId)!}",
				dataType : "json",
				type : "GET",
				headers: {"groupId": ${groupId}},
				success : function(result){
					var viewModel = kendo.observable({
						applicantName : function(){
							$('#applicantName').editable("setValue",result.applicantName); 
							return result.applicantName;
						},
						address : function(){
							$('#address').editable("setValue",result.address); 
							return result.address;
						},
						cityName : function(){
							$('#city').editable("setValue",result.cityCode); 
							return result.cityName;
						},
						districtName : function(){
							$('#district').editable("setValue",result.districtCode); 
							return result.districtName
						},
						wardName : function(){
							$('#wards').editable("setValue",result.wardCode); 
							return result.wardName
						},
						contactName : function(){
							$('#contactName').editable("setValue",result.contactName); 
							return result.contactName;
						},
						contactTelNo : function(){
							$('#phone').editable("setValue",result.contactTelNo); 
							return result.contactTelNo;
						},
						contactEmail : function(){
							$('#email').editable("setValue",result.contactEmail); 
							return result.contactEmail;
						},
						applicantIdDate : function(){
							return moment(result.applicantIdDate).format("DD/MM/YYYY");
						},
						applicantIdNo : result.applicantIdNo,
						userId : result.mappingUser.userId
					});

					kendo.bind($("#frmDetailAccount"), viewModel);
				},
				error : function(xhr){

				}
			});
		}

	}
	pullDetailProfile();

	$(document).on('click', '#change_avatar_profile', function(event){
		event.preventDefault();
		
		$("#avatar_file_profile").trigger({ type: "click" });
	});

	function profile_changeAvatarFileEntry(fileInput) {
		
		var files = fileInput.files;

		if (files.length > 0) {

			//show client
			for (var i = 0; i < files.length; i++) {
				
				var file = files[i];
				var imageType = /image.*/; 
				
				if ( i>0 ) {
					continue;
				}

				if (!file.type.match(imageType)) {
					continue;
				}
				
				var img=document.getElementById("profile_avatar_thumbnail");
				img.file = file;
				var reader = new FileReader();
				
				reader.onload = (function(aImg) { 
					
					return function(e) { 
						aImg.src = e.target.result;
					}; 

				})(img);
				
				reader.readAsDataURL(file);
				
			}

			var data = new FormData();
			data.append( 'fileName', $(fileInput)[0].files[0].name);
			data.append( 'fileType', $(fileInput)[0].files[0].type);
			data.append( 'fileSize', $(fileInput)[0].files[0].size);
			data.append( 'file', $(fileInput)[0].files[0]);

			$.ajax({
				type : 'PUT', 
				url  : '${api.server}/users/${userId}/photo', 
				data : data,
				processData: false,
				contentType: false,
				cache: false,
				headers: {
					groupId: ${groupId}
				},
				success :  function(result){ 
					notification.show({
						message: "Upload ảnh thành công!"
					}, "success");
				},
				error:function(result){
					notification.show({
						message: "Yêu cầu không thành công, xin vui lòng thử lại."
					}, "error");
				}
			});

		}

	}

	window.onload = function(){
		var urlReadFile = fileAttachmentUrl({
			method : "GET",
			url : "${api.server}/users/${userId}/photo",
			async : false,
			success: function(options){
				var urlOut = options.url;
				$('#profile_avatar_thumbnail').attr('src', urlOut);

			},
			error: function(){}
		});
	}


	function fileAttachmentUrl ( options) {

		var xhttp = new XMLHttpRequest();
		var a,filename;
		var data = {};

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState === 4 && xhttp.status === 200) {


				var disposition = xhttp.getResponseHeader('Content-Disposition');
				if (disposition && disposition.indexOf('attachment') !== -1) {
					var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
					var matches = filenameRegex.exec(disposition);
					if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
				}


				a = document.createElement('a');
				a.href = window.URL.createObjectURL(xhttp.response);

				var url = window.URL.createObjectURL(xhttp.response);


				options.success({url : url, status : xhttp.status});
			} else if (xhttp.readyState === 4 && xhttp.status !== 200) {
				options.error(xhttp.status);
			}

		};

		xhttp.open(options.method, options.url);
		xhttp.setRequestHeader("Content-Type", "application/json");
		xhttp.setRequestHeader("groupId", "${groupId}");


		if (options.hasOwnProperty("headers")){
			Object.keys( options.headers ).map(function(objectKey, index) {
				var value = options.headers[objectKey];
				xhttp.setRequestHeader(objectKey, value);
			});
		}


		if (options.hasOwnProperty("responseType")){
			xhttp.responseType = options.responseType;
		} else {
			xhttp.responseType = 'blob';
		}


		if (options.hasOwnProperty("data")){
			data = options.data;
		}


		xhttp.send(data);

	};

	console.log("${(applicantIdType)!}");
</script>