<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.tribeofspirit.common.domain.model.attribute.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>情绪检测</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/tribeofspirit.css" rel="stylesheet">
  <link href="js/bootstrap.js" rel="stylesheet">
  <!--<link rel="stylesheet" href="css/style.css">-->
</head>
<body>

<div class="container" id="detectResult">
    <div class="row total-score">
        <div class="col-xs-1 col-sm-1 col-md-1">
        </div>
        <div class="col-xs-14 col-sm-14 col-md-14">
            <h3>综合心情指数: <span id="expression"><c:choose><c:when test="${scanningResult.mood.equals(MoodType.Positive)}">+</c:when><c:otherwise>-</c:otherwise></c:choose>${scanningResult.moodScore}</span></h3>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4">
            <h3>
            <img id="like" src="images/like.png" class="img-responsive"/>
            </h3>
        </div>
        <div class="col-xs-1 col-sm-1 col-md-1">
        </div>
    </div>
  <div class="row detect-result">

    <div class="col-xs-1 col-sm-1 col-md-1">
    </div>
    <div class="col-xs-8 col-sm-8 col-md-8">
        <div class="thumbnail">
          <img id="toDetectPhoto" width="120" height="160" src="docstorage/${scanningResult.imageName}" />
        </div>
    </div>

    <div class="col-xs-1 col-sm-1 col-md-1">
    </div>
    <div class="col-xs-8 col-sm-8 col-md-8">
                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />
                <p>日期: ${date}</p>
                <p>性別: <span id="gender">${scanningResult.gender}</span></p>
                <p>年龄: <span id="age">${scanningResult.age}</span></p>
                <p>种族: <span id="ethnicity">${scanningResult.ethnicity}</span></p>
                <div class="btn-group btn-group-xs" role="group" aria-label="">
                    <button class="btn btn-default" id="actionToSender">给一个大大的拥抱</button>
                    <span></span>
                </div>

        </div>

    <div class="col-xs-1 col-sm-1 col-md-1">
    </div>
  </div>

  <div class="row detect-notes">
      <div class="col-xs-offset-2 col-xs-16">
        <lable>阳光如此灿烂</label>
      </div>
      <div class='col-xs-2'>
      </div>
  </div>

    <div class="row">
    <div class="btn-group btn-group-lg col-xs-20" role="group">
        <img height="60" src="images/button_try.png" class="img-responsive">
        <div class="try" id="try"><p>我也想试试</p></div>
    </div>
  </div>
</div>


</body>
<script src="js/jquery.min.js"> </script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!--
<script>

    $(document).ready(function () {
        $.post('detect/<c:out value="${mediaId}"/> ', {},
                    function(response, status){
                        var result = jQuery.parseJSON(response);
                        if(status == 'success' && result != null){
                            $("#expression").html(result.expressions.happiness.value);
                            $("#gender").html(result.gender.value);
                            $("#age").html(result.age.value);
                            $("#ethnicity").html(result.ethnicity.value);

                            $("#toDetectPhoto").attr("src", "docstorage/" + result.imageName);

                        }else{
                            alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
                        }

                    }, "json").fail(function() {
                        alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
                });

        $("#try").click(function(){
            location.href = "${pageContext.request.contextPath}/";
        });
    });




</script>-->
</html>