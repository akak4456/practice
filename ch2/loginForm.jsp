<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
       * { box-sizing:border-box; }
       a { text-decoration: none; }
        form {
            width:400px;
            height:500px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        input[type='text'], input[type='password'] {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        #title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        #msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <form action="<c:url value='/login/login'/>" method="post" onsubmit="return formCheck(this);">
        <h3 id="title">Login</h3>
        <div id="msg">${msg}</div>
        <input type="text" name="id" placeholder="이메일 입력" autofocus>
        <input type="password" name="pwd" placeholder="비밀번호">
        <button>로그인</button>
        <div>
            <label><input type="checkbox" name="rememberId"> 아이디 기억</label> |
            <a href="">비밀번호 찾기</a> |
            <a href="">회원가입</a>
        </div>
        <script>
            function formCheck(frm) {
                 var msg ='';
     
                 if(frm.id.value.length<3) {
                     setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                     return false;
                 }
     
                return true;
            }
     
            function setMessage(msg, element){
                 document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
     
                 if(element) {
                     element.select();
                 }
            }
        </script>
    </form>
</body>
</html>
