webpackJsonp([27],{226:function(t,e,a){var s=a(135)(a(558),a(570),null,null);t.exports=s.exports},558:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{citys:[],resultDistricts:[],resultWards:[],vnPostItems:[],postalServiceItems:[{itemName:"VNPOST",itemCode:"VNPOST"}],dichVuChuyenPhatKetQua:{viaPostal:0,postalServiceCode:"",postalServiceName:"",postalAddress:"",postalCityCode:"",postalCityName:"",postalDistrictCode:"",postalDistrictName:"",postalWardCode:"",postalWardName:"",postalTelNo:"",vnPostCode:""}}},computed:{loading:function(){return this.$store.getters.loading}},created:function(){},methods:{initData:function(t){var e=this;console.log("ket qua-----",t);var a={viaPostal:t.viaPostal,postalServiceCode:t.postalServiceCode,postalServiceName:t.postalServiceName,postalAddress:t.postalAddress,postalCityCode:t.postalCityCode,postalCityName:t.postalCityName,postalDistrictCode:t.postalDistrictCode,postalDistrictName:t.postalDistrictName,postalWardCode:t.postalWardCode,postalWardName:t.postalWardName,postalTelNo:t.postalTelNo,vnPostCode:t.vnPostCode};e.dichVuChuyenPhatKetQua=a,e.$nextTick(function(){var a={collectionCode:"ADMINISTRATIVE_REGION",level:0,parent:0};e.$store.getters.getDictItems(a).then(function(t){e.citys=t.data}),t.postalCityCode&&(a.parent=t.postalCityCode,a.level=1,e.$store.getters.getDictItems(a).then(function(t){e.resultDistricts=t.data})),t.postalDistrictCode&&(a.parent=t.postalDistrictCode,a.level=1,e.$store.getters.getDictItems(a).then(function(t){e.resultWards=t.data})),a={collectionCode:"VNPOST_CODE",level:0,parent:0}})},onChangeResultCity:function(t){var e=this,a={collectionCode:"ADMINISTRATIVE_REGION",level:1,parent:t};e.$store.getters.getDictItems(a).then(function(t){e.resultDistricts=t.data})},onChangeResultDistrict:function(t){var e=this,a={collectionCode:"ADMINISTRATIVE_REGION",level:2,parent:t};e.$store.getters.getDictItems(a).then(function(t){e.resultWards=t.data})},changeViaPostal:function(t){this.$emit("changeViapostal",t)}}}},570:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("v-card",[a("v-card-text",[a("v-slide-y-transition",[t.dichVuChuyenPhatKetQua.viaPostal?a("v-layout",{attrs:{wrap:""}},[a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Dịch vụ đăng ký: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm10:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-select",{attrs:{items:t.postalServiceItems,"item-text":"itemName","item-value":"itemCode",autocomplete:""},model:{value:t.dichVuChuyenPhatKetQua.postalServiceCode,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalServiceCode",e)},expression:"dichVuChuyenPhatKetQua.postalServiceCode"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("SĐT người nhận: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-text-field",{attrs:{"append-icon":"phone",required:t.dichVuChuyenPhatKetQua.viaPostal},model:{value:t.dichVuChuyenPhatKetQua.postalTelNo,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalTelNo",e)},expression:"dichVuChuyenPhatKetQua.postalTelNo"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Mã bưu cục: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-select",{attrs:{items:t.vnPostItems,"item-text":"itemName","item-value":"itemCode",required:t.dichVuChuyenPhatKetQua.viaPostal,autocomplete:""},model:{value:t.dichVuChuyenPhatKetQua.vnPostCode,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"vnPostCode",e)},expression:"dichVuChuyenPhatKetQua.vnPostCode"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:""}}),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Địa chỉ trả kết quả: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm10:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-text-field",{attrs:{"multi-line":"",rows:"2"},model:{value:t.dichVuChuyenPhatKetQua.postalAddress,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalAddress",e)},expression:"dichVuChuyenPhatKetQua.postalAddress"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Tỉnh/Thành phố: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-select",{attrs:{items:t.citys,"item-text":"itemName","item-value":"itemCode",autocomplete:""},on:{change:t.onChangeResultCity},model:{value:t.dichVuChuyenPhatKetQua.postalCityCode,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalCityCode",e)},expression:"dichVuChuyenPhatKetQua.postalCityCode"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Quận/Huyện: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-select",{attrs:{items:t.resultDistricts,"item-text":"itemName","item-value":"itemCode",autocomplete:""},on:{change:t.onChangeResultDistrict},model:{value:t.dichVuChuyenPhatKetQua.postalDistrictCode,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalDistrictCode",e)},expression:"dichVuChuyenPhatKetQua.postalDistrictCode"}})],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-subheader",{staticClass:"pl-0"},[t._v("Xã/Phường: ")])],1),t._v(" "),a("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-select",{attrs:{items:t.resultWards,"item-text":"itemName","item-value":"itemCode",autocomplete:""},model:{value:t.dichVuChuyenPhatKetQua.postalWardCode,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"postalWardCode",e)},expression:"dichVuChuyenPhatKetQua.postalWardCode"}})],1)],1):a("span",[a("v-icon",{attrs:{size:"16"}},[t._v("check_circle")]),t._v(" "),a("strong",[t._v("Đăng ký kết quả tại nhà")]),t._v(" để điền thông tin chuyển phát tận nhà\n        ")],1)],1)],1)],1),t._v(" "),a("div",{staticClass:"absolute__btn",staticStyle:{width:"198px","margin-top":"4px"}},[t.loading?a("content-placeholders",{staticClass:"mt-1"},[a("content-placeholders-text",{attrs:{lines:1}})],1):a("v-checkbox",{attrs:{label:"Đăng ký kết quả tại nhà",value:2},on:{change:t.changeViaPostal},model:{value:t.dichVuChuyenPhatKetQua.viaPostal,callback:function(e){t.$set(t.dichVuChuyenPhatKetQua,"viaPostal",e)},expression:"dichVuChuyenPhatKetQua.viaPostal"}})],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=27.28ef87f3a7d8fd45578a.js.map