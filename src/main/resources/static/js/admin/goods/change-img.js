/**
 * 
 */
function changedImg(element) {
	// 현재 input의 부모인 label을 찾기
	var label = $(element).closest('.btn-file');

	// label의 형제 중 name이 'newName'인 hidden input 태그를 찾아 제거
    label.find('input[type=hidden][name="newName"]').remove();

	console.log($(element)[0].files[0]);

	var formData = new FormData();
	formData.append("img", $(element)[0].files[0]);

	$.ajax({
		url: "/admin/temp-upload",
		data: formData,
		type: "post",
		contentType: false,
		processData: false,
		success: function(result) {
			var newName = $("<input type='hidden' name='newName'>").val(result.newName);

			//$(element).parents(".btn-file")
			$(element).parent().css("background-image", `url(${result.url})`);
			//tempkey.val(result.orgName);
			$(element).after(newName);
			//$(element).siblings(".orgName").val(result.orgName);
		}
	});
}