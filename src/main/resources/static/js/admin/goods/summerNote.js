/**
 * 
 */
$(document).ready(function() {
	$('#summernote').summernote({
		toolbar: [
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
			['color', ['forecolor', 'color']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['insert', ['picture', 'link', 'video']],
			['view', ['fullscreen', 'help']]
		],
		height: "500",
		lang: "ko-KR",
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
		callbacks: {	//여기 부분이 이미지를 첨부하는 부분
			onImageUpload: function(files) {

				uploadSummernoteImageFile(files[0], this);

			},
			onPaste: function(e) {
				var clipboardData = e.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
			}
		}
	});
});
/**
*이미지 파일 업로드
*/
function uploadSummernoteImageFile(file, editor) {

	var formData = new FormData();
	formData.append("img", file);

	$.ajax({
		data: formData,
		type: "POST",
		url: "/admin/temp-upload",
		contentType: false,
		processData: false,
		success: function(result) {
			//항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', result.url);

		}
	});
}