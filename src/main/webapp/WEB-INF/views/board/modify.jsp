<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">Tables</h1>


	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Board List Page</h6>
		</div>
		<div class="card-body">
			<form method="post" id="modifyform">
				<div class="form-group">
					<label for="bno">bno</label> <input type="text"
						class="form-control" value="${board.bno}" id="bno" name="bno"
						readonly>
				</div>
				<div class="form-group">
					<label for="title">title</label> <input type="text"
						class="form-control" value="${board.title}" id="title"
						name="title">
				</div>
				<div class="form-group">
					<label for="comment">content</label>
					<textarea rows="10" class="form-control" id="comment"
						name="content">${board.content}</textarea>
				</div>
				<div class="form-group">
					<label for="writer">writer</label> <input type="text"
						class="form-control" id="writer" value="${board.writer}"
						name="writer" readonly>
				</div>


				<div class="form-group">
					<label for="file">file <br> <span
						class="btn btn-primary">파일 첨부</span></label> <input type="file"
						class="form-control d-none" id="file" name="file" multiple>
					<div class="uploadResult my-3">
						<ul class="list-group file-name my-3">
						
						<c:forEach items="${board.attachs}" var="attach">
						<c:if test="${not empty board.attachs[0].uuid}">
							<li class="list-group-item" data-uuid="${attach.uuid}"
								data-name="${attach.name}" data-path="${attach.path}"
								data-image="${attach.image}"><a
								href="/download${attach.url}&thumb=off"> <i
									class="far fa-file"></i>${attach.name}
							</a>
							 <i style="color: red; "class="far fa-times-circle btn-remove float-right" data-uuid="${attach.uuid}" data-file="${attach.url}&thumb=off"></i>
							</li>
						</c:if>
						</c:forEach>
						</ul>
						<ul class="nav thumbs">
							<c:forEach items="${board.attachs}" var="attach">
								<c:if test="${attach.image}">
									<li class="nav-link m-2" data-uuid="${attach.uuid}"><a
										class="img-thumb" href=""> <img class="img-thumbnail"
											src="/display${attach.url}&thumb=on"
											data-src="${attach.url}&thumb=off">
									</a></li>

								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
				<sec:authorize access="isAuthenticated() and principal.username eq #board.writer">
				<button class="btn btn-warning" formaction="modify">modify</button>
				<button class="btn btn-danger" formaction="remove">remove</button>
				</sec:authorize>
				<a href="list${cri.fullQueryString}" class="btn btn-secondary">list</a>
				<input type="hidden" name="pageNum" value="${cri.pageNum}">
				<input type="hidden" name="amount" value="${cri.amount}"> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>
		</div>
	</div>


	<jsp:include page="../includes/footer.jsp"></jsp:include>
<script>
		$(function() {
			var csrfHeader = '${_csrf.headerName}';
			var csrfToken = '${_csrf.token}';
			$("form button[formaction='modify']").click(function() {
				event.preventDefault();
				//title, content, writer, attachs[0].uuid
				var str = '';
				
				$(".file-name li").each(function(i) {
					var uuid = $(this).data('uuid');
					str += `
					<input type="hidden" name="attachs[\${i}].uuid" value="\${uuid}">
					<input type="hidden" name="attachs[\${i}].name" value="\${$(this).data('name')}">
					<input type="hidden" name="attachs[\${i}].path" value="\${$(this).data('path')}">
					<input type="hidden" name="attachs[\${i}].image" value="\${$(this).data('image')}">
					`;
					
				})
				$("#modifyform").attr("action", "modify")
				$("#modifyform").append(str);
				$("#modifyform").submit();
			})

			function checkExtension(files) {

				const MAX_SIZE = 5 * 1024 * 1024;
				const EXCLUDE_EXT = new RegExp("(.*?)\.(js|jsp|asp|php)");
				for ( let i in files) {
					if (files[i].size >= MAX_SIZE
							|| EXCLUDE_EXT.test(files[i].name)) {
						return false;
					}
				}
				return true;

			}

			$("#file").change(function() {
				event.preventDefault();
				let files = $(":file").get(0).files;

				if (!checkExtension(files)) {
					alert("조건 불일치")
					return false;
				}
				let formData = new FormData();

				for ( let i in files) {
					formData.append("files", files[i])
				}

				$.ajax({
					url : "/uploadAjax",
					processData : false,
					contentType : false,
					beforeSend : function (xhr) {
						xhr.setRequestHeader(csrfHeader, csrfToken)
					},
					data : formData,
					method : "post",
					success : function(data) {
						$("form").get(0).reset();
						showUploadedFile(data);
					}

				})
			})
			function showUploadedFile(uploadResultArr) {
				var str = "";
				var imgStr = "";
				for ( var i in uploadResultArr) {
					let obj = uploadResultArr[i];
					str += `<li class="list-group-item" data-uuid="\${obj.uuid}" data-path="\${obj.path}" data-name="\${obj.name}" data-image="\${obj.image}" ><a href="/download\${obj.url}"><i class="far fa-file"></i>`;
					str += obj.name
							+ `</a> <i style="color: red; "class="far fa-times-circle btn-remove float-right" data-file="\${obj.url}"></i></li>`

					if (obj.image) {
						imgStr += `<li class="nav-link m-2" data-uuid="\${obj.uuid}"><a class="img-thumb" href=""><img class="img-thumbnail" src="/display\${obj.url}&thumb=on" data-src="\${obj.url}" ></a></li>`;
					}
				}
				$(".uploadResult .file-name").append(str);
				$(".uploadResult .thumbs").append(imgStr);
			}
			$(".uploadResult ul").on("click", ".btn-remove", function() {
				var uuid = $(this).data("uuid");
				console.log(file)
				$('[data-uuid="' + uuid + '"]').remove()
				
				
/* 				$.getJSON("/deleteFile?" + file, (function(data) {
					console.log(data);
					$this.parent().remove();

				}) */

			})
			$(".uploadResult ul").on("click",".img-thumb" ,function() {
				event.preventDefault();
				var param = $(this).find("img").data("src")
				$("#showImageModal").find("img").attr("src", "/display" + param).end().modal("show");		
			})

			 $( ".file-name").sortable({
				 sort : function(event, ui) {
					
				},
				change : function () {
					console.log(this)
				}
				 
			 }).css({cursor:"move"});

		})
	</script>
	</html>