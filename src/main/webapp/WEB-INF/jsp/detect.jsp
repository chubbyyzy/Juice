<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<body class="checking">

<div id="inprogress" style="height:500px">
    <img src="images/loadingicon.png" class="img-center center-block"/>
</div>

<div id="detectResult" class="loading">

<div class="container-fluid">
    <img src="images/topbg.png" width="100%" height="50px"/>
    <div class="general-desc">
        <h5>综合心情指数: 非常开心</h5>
    </div>
    <div class="score">
        <h3><label id="expression"></label></h3>
    </div>
</div>


<div class="container">

    <div class="row middle">
        <div class="col-xs-6 col-sm-6 col-md-6 action-left">
            <h6 id="tryAgain"> <<再试一次</h6>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 photo img-border">
            <img  id="toDetectPhoto" src="images/noname_photo.jpg" class="img-circle" width="100%" height="auto">
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 action-right">
            <h6 id="shareIt"> 炫耀一下>></h6>
        </div>
    </div>

    <div class="row detail">
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            <jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />
            日期: ${date}
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            年龄: <label id="age"></label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row detail">
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            性别: <label id="gender"></label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            来自: <label id="ethnicity"></label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row task-seperator"></div>

    <div class="row detail">
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-16 col-sm-16 col-md-16">
            <div id="expressiondescription"></div>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>

    <div class="row task">
        <div class="col-xs-1 col-sm-1 col-md-1 task-detail">
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2 task-detail">
            <h5><img src="images/loadingicon.png" width="50%"></h5>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 task-detail ">
            <h5 clas="task-desc">
                给身边离你最近的人一个大大的拥抱</h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2 task-check">
            <h5><input type="checkbox" class="check-task"></h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row task-seperator"></div>

    <div class="row task">
        <div class="col-xs-1 col-sm-1 col-md-1 task-detail">
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2 task-detail">
            <h5><img src="images/loadingicon.png" width="50%"></h5>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 task-detail ">
            <h5 clas="task-desc">
                给身边离你最近的人一个大大的拥抱</h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2 task-check">
            <h5><input type="checkbox" class="check-task"></h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row task-seperator"></div>

    <div class="row task">
        <div class="col-xs-1 col-sm-1 col-md-1 task-detail">
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2 task-detail">
            <h5><img src="images/loadingicon.png" width="50%"></h5>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 task-detail ">
            <h5 clas="task-desc">
                给身边离你最近的人一个大大的拥抱</h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2 task-check">
            <h5><input type="checkbox" class="check-task"></h5>
        </div>

        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>

    <div class="row">

        <div class="footer">
            <img src="images/bottombg.png" width="100%" />
               <span class="help">        <h4>求助好友帮忙完成任务>></h4>
                </span>
        </div>

    </div>

</div>
</div>

</body>
<script src="js/jquery.min.js"> </script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script>

    var jsApiList = [
        'onMenuShareTimeline',
        'onMenuShareAppMessage'
    ];

    $(document).ready(function () {
        $("#inprogress").show();
        $("#detectResult").hide();
        initPage();
    });

    function initPage() {
        $.post("wxConfig?_method=get", {"url": window.location.href}, function (data, status) {
            data.debug = false;
            data.jsApiList = jsApiList;
            wx.config(data);
        });
    }

    $("#tryAgain").click(function(){
        location.href = "${pageContext.request.contextPath}/";
    });

    var g_scanningid="";
    var resultImage = "";

    wx.ready(function(){

        $.post('detect/<c:out value="${mediaId}"/>', {},
            function(response, status){
                var result = jQuery.parseJSON(response);
                if(status == 'success' && result != null){
                    var overviewDisplay=getOverviewPointDisplay(result.mood.confidence, result.mood.value);
                    $("#expression").html(overviewDisplay);
                    $("#gender").html(result.gender.value);
                    $("#age").html(result.age.value);
                    $("#ethnicity").html(result.ethnicity.value);

                    $("#toDetectPhoto").attr("src", "docstorage/" + result.imageName);

                    var expressionMessage = getExpressionDescription(result.expressions);
                    $("#expressiondescription").html(expressionMessage);
                    g_scanningid = result.scanningId;

                    $('body').removeClass("checking");
                    $("#detectResult").show();
                    $("#inprogress").hide();
                }else{
                 alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
                }

            }, "json").fail(function() {
                alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
        });

        wx.onMenuShareTimeline({
                title: '情绪检测', // 分享标题
                link: '<c:out value="${hostUrl}"/>/share?scanningid='+g_scanningid, // 分享链接
                imgUrl: '<c:out value="${hostUrl}"/>/images/icon.jpg', // 分享图标
                success: function () {
                    alert("分享成功");
                },
                cancel: function () {
                    alert("取消分享");
                }
        });

        wx.onMenuShareAppMessage({
            title: '情绪检测分享', // 分享标题
            desc: '情绪分享', // 分享描述
            link: '<c:out value="${hostUrl}"/>/share?scanningid='+g_scanningid, // 分享链接
            imgUrl: '<c:out value="${hostUrl}"/>/images/icon.jpg', // 分享图标
            success: function () {
                alert("分享成功");
            },
            cancel: function () {
                alert("取消分享");
            }
        });

    });

    wx.error(function (res) {
            alert(res.errMsg);
    });

    function getOverviewPointDisplay(confidencePoint, confidenceType)
    {
        var overviewPoint;
        if(confidenceType == 'Positive')
            overviewPoint = "+"+confidencePoint;
        else
            overviewPoint = "-"+confidencePoint;

        return overviewPoint;

    };

    function getExpressionDescription(expressionNode)
    {
        expressionResult={};
        expressionResult.sadness =  expressionNode.sadness.value;
        expressionResult.neutral = expressionNode.neutral.value;
        expressionResult.disgust = expressionNode.disgust.value;
        expressionResult.anger = expressionNode.anger.value;
        expressionResult.surprise = expressionNode.surprise.value;
        expressionResult.fear = expressionNode.fear.value;
        expressionResult.happiness = expressionNode.happiness.value;

        var expressionWithMaxScore={itemName:"", itemValue:0};

        for (var subItem in expressionResult){
            if(expressionResult[subItem]>expressionWithMaxScore.itemValue)
            {
                expressionWithMaxScore.itemName = subItem;
                expressionWithMaxScore.itemValue = expressionResult[subItem];
            }
        }


        correspondingExpression = expressionWithMaxScore.itemName;
        correspondingMessageList = espressionMessage[correspondingExpression];
        randomMessageIndex = Math.floor((Math.random() * 2) + 1);
        randomMessage = correspondingMessageList[randomMessageIndex];
        return randomMessage;


    };

    espressionMessage = {
        neutral : ["平平淡淡总是真","您今日真是心如止水","您真是波澜不惊"],
        sadness : ["您正处在沮丧低压带","什么事伐开心呀，西湖水都要流泪了","此情无计可消除,兄台何事悲伤？"],
        happiness : ["您真是神采奕奕","快乐似神仙","爱笑的人有福哦"],
        supprise : ["您刚刚大吃了一斤吗？","什么情况？！","都吃惊到目瞪口僵了！"],
        anger : ["您今天笼罩了一朵生气乌云","哟，气鼓鼓的要变河豚呀","愤怒低压带在形成哦"],
        disgust : ["您反感感冒了吗？","吐着吐着就吐习惯了","被什么恶心到了？"],
        fear : ["您刚刚从恐惧森林里出来？","莫怕莫怕，常来拍拍","刚看了恐怖故事吗？"]
    };


</script>
</html>