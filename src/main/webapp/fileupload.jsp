<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>文件上传</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		$('#frmupload1').submit(function() {
			if ($('#file1').val() == '') {
				alert('请选择上传导入文件!');
				$('#file1').focus();
				return false;
			} else {
				if (!isvalidatefile($('#file1').val()))
					return false;

			}
		});
		$('#frmupload2').submit(function() {
			if ($('#file2').val() == '') {
				alert('请选择上传导入文件!');
				$('#file2').focus();
				return false;
			} else {
				if (!isvalidatefile($('#file2').val()))
					return false;

			}
		});
	});

	function isvalidatefile(obj) {
		var extend = obj.substring(obj.lastIndexOf(".") + 1);
		//alert(extend);  
		if (extend == "") {
		} else {
			if (!(extend == "xls")) {
				alert("请上传后缀名为xls(Excel2003)或xlsx(Excel2007)的文件!");

				return false;
			}
		}
		return true;
	}
</script>
</head>

<body>
	<h1>上传文件</h1>
	<form action="" method="post" enctype="multipart/form-data" onsubmit="return checkSubmit();">

		<p>请选择文件：</p>

		<p ${not empty errors ?"style='color : red;'":""}>${errors}</p>
		<input type="file" name="file" id="file" />&nbsp;<input type="submit" value="确定" />
	</form>
</body>
</html>
