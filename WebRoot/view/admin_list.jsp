<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>管理员列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="../css/style.css" tppabs="../css/style.css" />
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<style>
		body{padding:20px; width:800px; margin:0 auto;}
		h3{color:gray;}
		table{text-align:center;}
		th{text-align: center;}
		img{width:20px;height:20px;}
		.icon{padding: 5px;}
		.buttons {margin:5% 0 -7% 2%;}
		button{margin-right: 5px;}
		#passed{display: none}
		.fuc{text-align: right;}
	</style>	
	<script src="../js/jquery-1.9.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script>
		function allowOneByCheck(id){
			$.ajax({
				url:"AuditAction/allowOneByCheck.do",
				datatype:"json",
				type:"post",
				data:{
					id:id
				}
			});
		}
		
		function deleteOneByCkeck(id){
			$.ajax({
				url:"AuditAction/deleteOneByCheck.do",
				datatype:"json",
				type:"post",
				data:{
					id:id
				}
			});
		}
		
		function getAuditted(){
			$.ajax({
				url:"AuditAction/getAdministrator.do",
				type:"post",
				datatype:"json",
				data:{
					flag:0
				},
				success:function(da){
					var data = eval('('+da+')');
					var html = "";
					for(var i=0;i<data.length;i++){
						html+="<tr>"+
								"<td><input type='checkbox' value='"+data[i].id+"'></td>"+
								"<td>"+data[i].id+"</td>"+
								"<td>"+data[i].name+"</td>"+
								"<td>"+data[i].count+"</td>"+
								"<td><a class='icon_delete1' ><img src='../img/delete.png' alt=''></a></td>"+
								"</tr>";
					}
					$("#tbody_allowed").empty().append(html);
					$(".icon_delete1").click(function(){
					   var id = $(this).parent().parent().find("input").val();
					   deleteOneByCkeck(id);
					   $(this).parent().parent().remove();
					});
				}
			});
		}
		
		
		
		function getUnAudittedd(){
			$.ajax({
				url:"AuditAction/getAdministrator.do",
				type:"post",
				datatype:"json",
				data:{
					flag:1
				},
				success:function(da){
					var data = eval('('+da+')');
					var html = "";
						for(var i=0;i<data.length;i++){
						html+="<tr>"+
								"<td><input type='checkbox' value='"+data[i].id+"'></td>"+
								"<td>"+data[i].id+"</td>"+
								"<td>"+data[i].name+"</td>"+
								"<td>"+data[i].count+"</td>"+
								"<td><a href='#' class='icon_allow' ><img src='../img/pass.png' alt=''></a>  <a class='icon_delete' href='#' ><img src='../img/delete.png' alt=''></a></td>"+
								"</tr>";
					}
					$("#tbody_Unallowed").empty().append(html);
					$(".icon_delete").click(function(){
				       var id = $(this).parent().parent().find("input").val();
					   deleteOneByCkeck(id);
					   $(this).parent().parent().remove();
					});
					$(".icon_allow").click(function(){
						var id = $(this).parent().parent().find("input").val();
						allowOneByCheck(id);
						$(this).parent().parent().remove();
					});
				}
			});
		}
	
	$(document).ready(function() {
			getUnAudittedd();
			$("#btn-review").click(function(){
				getUnAudittedd();
			});
			$("#btn-passed").click(function(){
				getAuditted();
			});
			
			$("#allowed_delete").click(function(){
				var ids = "";
				$("#tbody_allowed input[type=checkbox]:checked").each(function(){
					ids +=$(this).val()+",";
				});
				if(ids==""){
					alert("请选择要操作的对象");
				}else{
					$.ajax({
						url:"AuditAction/changeByChecked.do",
						datatype:"json",
						type:"post",
						data:{
							ids:ids,
							flag:3
						},
						success:function(data){
							getAuditted();
						}
					});
				}
			});
			
			$("#allowByCheck").click(function(){
				var id=""; 
				$("#tbody_Unallowed input[type=checkbox]:checked").each(function(){
					id+=$(this).val()+",";
				});
				if(id==""){
					alert("请选择要操作的对象");
				}else{
					$.ajax({
						url:"AuditAction/changeByChecked.do",
						datatype:"json",
						type:"post",
						data:{
							ids:id,
							flag:1
						},
						success:function(data){
							getUnAudittedd();
						}
					});
				}
			});
			
			$("#deleteByCkeck").click(function(){
				var ids = "";
				$("#tbody_Unallowed input[type=checkbox]:checked").each(function(){
					ids +=$(this).val()+",";	
				});
				if(ids==""){
					alert("请选择要操作的对象");
				}else{
					$.ajax({
						url:"AuditAction/changeByChecked.do",
						datatype:"json",
						type:"post",
						data:{
							ids:ids,
							flag:2
						},
						success:function(data){
							getUnAudittedd();
						}
					});
				}
			});
		});
	</script>
</head>
<body>
	<blockquote>
		<h3>管理员信息</h3>
		<div class="btn-group">
		<button id="btn-review" class="btn btn-info" >未审核</button>
		<button id="btn-passed" class="btn" >已审核</button>
		</div>
	
	</blockquote>
	<div id="review">
		
		<table  class="table table-responsive">
			<thead>
				<tr class="info">
					<th><input type="checkbox"></th>
					<th>ID</th>
					<th>姓名</th>
					<th>账号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody_Unallowed">
			
			</tbody>
		</table>
		<div class="buttons">
			<button type="button" id="allowByCheck" class="btn btn-primary">通过</button>
			<button type="button" id="deleteByCkeck" class="btn btn-danger">驳回</button>
		</div>

	</div>




	<div id="passed">
		
		<table  class="table table-responsive">
			<thead>
				<tr class="info">
				<th><input type="checkbox"></th>
				<th>ID</th>
				<th>姓名</th>
				<th>账号</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="tbody_allowed">
			
			</tbody>
			</table>
		<div class="buttons">
			<button type="button" id="allowed_delete" class="btn btn-danger">驳回</button>
		</div>

	</div>



		<nav class="text-right">
		  <ul class="pagination">
		    <li><a href="#">&laquo;</a></li>
		    <li class="active"><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">&raquo;</a></li>
		  </ul>
		</nav>



	<script type="text/javascript">
		var p_r = "../img/p_r.png";
		var p_b = "../img/p_b.png";
		var d_r = "../img/d_r.png";
		var d_b = "../img/d_b.png";
		$(".pass").click(function(event) {
			var old_src = $(this).attr('src');
			var new_src = old_src==p_r?p_b:p_r;
			$(this).attr('src',new_src);
			$(this).parent().next().find('.delete').attr('src',d_b);
		});

		$(".delete").click(function(event) {
			var old_src = $(this).attr('src');
			var new_src = old_src==d_r?d_b:d_r;
			$(this).attr('src',new_src);
			$(this).parent().prev().find('.pass').attr('src',p_b);
		});

		$("#btn-passed").click(function(event) {
			$(this).addClass('btn-info')
			$('#btn-review').removeClass('btn-info');
			$("#review").hide();
			$('#passed').show();
		});

		$("#btn-review").click(function(event) {
			$(this).addClass('btn-info')
			$('#btn-passed').removeClass('btn-info');
			$("#passed").hide();
			$('#review').show();
		});
				

	</script>
</body>
</html>