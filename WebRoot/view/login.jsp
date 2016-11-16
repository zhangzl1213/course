<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="../css/style.css" tppabs="../css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
#login_error{color:red;}
</style>
<script src="../js/jquery.js"></script>
<script src="../js/verificationNumbers.js" tppabs="../js/verificationNumbers.js"></script>
<script src="../js/Particleground.js" tppabs="../js/Particleground.js"></script>
<script>
       
$(document).ready(function() {
	 $("#submit").click(function(){
  					var identify = $("input[name='RadioButtonList1']:checked").val();
  					if(identify==1){
						$.ajax({
						url:"loginAction/executive_staff_login.do",
						type:"post",
						datatype:"json",
						data:{
							count:$("#count").val(),
							password:$("#password").val(),
						},
						success:function(data){
							$("#login_error").text(data);
							$("#count").val("");
							$("#password").val("");
							$("#J_codetext").val("");
							}
						});		  						
  					}else{
  						$.ajax({
						url:"loginAction/administrator_login.do",
						type:"post",
						datatype:"json",
						data:{
							count:$("#count").val(),
							password:$("#password").val(),
						},
						success:function(data){
							$("#login_error").text(data);
							$("#count").val("");
							$("#password").val("");
							$("#J_codetext").val("");
						}
					});
  				}
			});
			
 $("#re_count").blur(function(){
 	if($("#re_count").val()!=""){
 		$.ajax({
 			url:"registerAction/checkCountOrName.do",
 			type:"post",
 			datatype:"json",
 			data:{
 				count:$("#re_count").val()
 			},
 			success:function(data){
 				var obj = eval('('+data+')'); 
 				if(obj.flag!=1) $("#login_error").text("该账号已被注册");
 			}
 		});
 	}
 });
 
 $("#re_name").focus(function(){
 	$("#login_error").text("");
 });
 
  $("#re_count").focus(function(){
 	$("#login_error").text("");
 });
 
  $("#re_name").blur(function(){
 	if($("#re_name").val()!=""){
 		$.ajax({
 			url:"registerAction/checkCountOrName.do",
 			type:"post",
 			datatype:"json",
 			data:{
 				name:$("#re_name").val()
 			},
 			success:function(data){
 				var obj = eval('('+data+')'); 
 				if(obj.flag!=1) $("#login_error").text("该用户名已被注册");
 			}
 		});
 	}
 });
 
 
 $("#register").click(function(){
	var re_password = $("#re_password").val();
	var sure_passwod = $("#sure_password").val();
	if(re_password==sure_passwod&&$("#login_error").text()==""){
		$.ajax({
			url:"registerAction/administrator_register.do",
			type:"post",
			datatype:"json",
			data:{
				name:$("#re_name").val(),
				count:$("#count").val(),
				password:$("#re_password").val(),
			},
			success:function(data){
				var obj = eval('('+data+')');
				if(obj.flag==1){
					$("#login_error").html(
						
					);
				}else{
					window.location,href="";
				}
			}
		});
	}else{
		$("#login_error").text("请输入有效信息");
		$("#re_name").val("");
		$("#re_password").val("");
		$("#re_count").val("");
	    $("#sure_password").val("");
	}
});

$('#switch_qlogin').click(function(){
		$("#login_error").text("");
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		
		});
	$('#switch_login').click(function(){
		$("#login_error").text("");
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});

  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //登录状态验证
  // $('#login_error').hide();
  //验证码
  createCode();
  //测试提交，对接程序删除即可
  $(".submit_btn").click(function(){
	  location.href="javascrpt:;"/*tpa=http://***index.html*/;
	  });
});
</script>
</head>
<body>
<dl class="admin_login">
 <dt>
  <strong>视频播放系统后台管理</strong>
  <em>Management System</em>
 </dt>
<div class="header">      
	 <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>

			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>  
 <error id="login_error"></error>  
<div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
<dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="count" name="count"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" id="password" name="password"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
 </dd>
 <dd>
				<td><input id="RadioButtonList1_0" type="radio"
					name="RadioButtonList1" value="1" tabindex="4" /><label
					for="RadioButtonList1_0">主管 </label></td>
				<td><input id="RadioButtonList1_2" type="radio"
					name="RadioButtonList1" value="2" checked="checked" tabindex="4" /><label
					for="RadioButtonList1_2">管理员</label>
			</dd>
 <dd>
<input type="submit" value="登 录" id="submit"  style="width:150px;" class="button_blue">
 </dd>
 <dd>
</div>
</div>
</div>
<!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
 <dd class="user_icon">
  <input type="text" placeholder="用户名" class="login_txtbx" id="re_name"/>
 </dd>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="re_count"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" id="re_password"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="确认密码" class="login_txtbx" id="sure_password"/>
 </dd>
<dd>
<input type="button" value="同意并注册" class="button_blue" id="register"/>
</dd>
    </div>
    <!--注册end--> 
 </dd>
</dl>
</body>
</html>
