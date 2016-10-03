<%--@elvariable id="scanningResult" type="com.tribeofspirit.domain.model.FaceScanningResult"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.tribeofspirit.domain.model.attribute.*" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>情绪检测</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/tribeofspirit.css?v=1" rel="stylesheet">
  <link href="js/bootstrap.js" rel="stylesheet">
  <!--<link rel="stylesheet" href="css/style.css">-->
</head>
<body>


<div id="detectResult">

<div class="container-fluid">
    <img src="images/topbg.png" width="100%" height="50px"/>
    <div class="general-desc">
        <h5>综合指数</h5>
    </div>
    <div class="score">
        <h3><label id="expression"><c:choose><c:when test="${scanningResult.mood eq 'Positive'}">+</c:when><c:otherwise>-</c:otherwise></c:choose>
        ${scanningResult.moodScore}</label>
        </h3>
    </div>
</div>


<div class="container">

    <div class="row middle">
        <div class="col-xs-6 col-sm-6 col-md-6 action-left">
            <h6> &lt;&lt;再试一次</h6>
        </div>
        <div class="col-xs-8 col-sm-8 col-md-8 photo img-border">
            <img  id="toDetectPhoto" src="docstorage/${scanningResult.imageName}" class="img-circle" width="100%" height="auto">
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 action-right">
            <h6> 炫耀一下&gt;&gt;</h6>
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
            年龄: <label id="age">${scanningResult.age}</label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row detail">
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            性别: <label id="gender">${scanningResult.gender}</label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-7 col-sm-7 col-md-7">
            来自: <label id="ethnicity">${scanningResult.ethnicity}</label>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row task-seperator"></div>

    <div class="row detail">
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
        <div class="col-xs-16 col-sm-16 col-md-16">
            <div id="expressiondescription">&nbsp;</div>
            <div>${scanningResult.tabooAndAppropriate}</div>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2">
        </div>
    </div>

    <div class="row task">
        <div class="col-xs-1 col-sm-1 col-md-1"></div>
        <div class="col-xs-18 col-sm-18 col-md-18 ">
            <div class="row task-detail">
                <div class="col-xs-1 col-sm-1 col-md-1"><div class="taskcheckbox taskcheckboxoff"></div></div>
                <div class="col-xs-19 col-sm-19 col-md-19 task-desc">
                               给身边离你最近的人一个大大的拥抱</div>
            </div>
        </div>

    </div>
    <div class="row task-seperator"></div>

    <div class="row task">
            <div class="col-xs-1 col-sm-1 col-md-1"></div>
            <div class="col-xs-18 col-sm-18 col-md-18 ">
                <div class="row task-detail">
                    <div class="col-xs-1 col-sm-1 col-md-1"><div class="taskcheckbox taskcheckboxoff"></div></div>
                    <div class="col-xs-19 col-sm-19 col-md-19 task-desc">
                                   给身边离你最近的人一个大大的拥抱</div>
                </div>
            </div>

    </div>
    <div class="row task-seperator"></div>

    <div class="row task">
            <div class="col-xs-1 col-sm-1 col-md-1"></div>
            <div class="col-xs-18 col-sm-18 col-md-18 ">
                <div class="row task-detail">
                    <div class="col-xs-1 col-sm-1 col-md-1"><div class="taskcheckbox taskcheckboxoff"></div></div>
                   <div class="col-xs-19 col-sm-19 col-md-19 task-desc">
                                   给身边离你最近的人一个大大的拥抱</div>
                </div>
            </div>

    </div>

    <div class="row">

        <div class="footer">
            求助好友帮忙完成任务>>
        </div>

    </div>

    <div class="modal fade" id="modaldiv">
        <div id="inprogress" class="inprogress">
            <img src="images/loadingicon.png" class="inprogressimage">
        </div>
        <div>
            <img src="images/selectimage.png" style="display: block;margin-left: auto;margin-right: auto;">
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
        //$("#inprogress").show();
        //$("#detectResult").hide();
        initPage();
        $(".task-detail").bind("click",selectTask);
        $(".footer").bind("click",askForHelp);
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



    function getExpressionDescription()
    {
        expressionResult={};
        expressionResult.sadness =  ${scanningResult.sadnessScore};
        expressionResult.neutral = ${scanningResult.neutralScore};
        expressionResult.disgust = ${scanningResult.disgustScore};
        expressionResult.anger = ${scanningResult.angerScore};
        expressionResult.surprise = ${scanningResult.suppriseScore};
        expressionResult.fear = ${scanningResult.fearScore};
        expressionResult.happiness = ${scanningResult.happinessScore};

        var expressionWithMaxScore={itemName:"", itemValue:0};

        for (var subItem in expressionResult){
            if(expressionResult[subItem]>expressionWithMaxScore.itemValue)
            {
                expressionWithMaxScore.itemName = subItem;
                expressionWithMaxScore.itemValue = expressionResult[subItem];
            }
        }


        correspondingExpression = expressionWithMaxScore.itemName;
        correspondingMessageList = expressionMessages[correspondingExpression];
        randomMessageIndex = Math.floor((Math.random() * 2) + 0);
        randomMessage = correspondingMessageList[randomMessageIndex];
        return randomMessage;


    };

    wx.ready(function(){

        wx.onMenuShareTimeline({
                title: '情绪检测', // 分享标题
                link: '<c:out value="${hostUrl}"/>/share?scanningid=${scanningResult.id}', // 分享链接
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
            link: '<c:out value="${hostUrl}"/>/share?scanningid=${scanningResult.id}', // 分享链接
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
    expressionMessages = {
        neutral : ["平平淡淡总是真","您今日真是心如止水","您真是波澜不惊"],
        sadness : ["您正处在沮丧低压带","什么事伐开心呀，西湖水都要流泪了","此情无计可消除,兄台何事悲伤？"],
        happiness : ["您真是神采奕奕","快乐似神仙","爱笑的人有福哦"],
        surprise : ["您刚刚大吃了一斤吗？","什么情况？！","都吃惊到目瞪口僵了！"],
        anger : ["您今天笼罩了一朵生气乌云","哟，气鼓鼓的要变河豚呀","愤怒低压带在形成哦"],
        disgust : ["您反感感冒了吗？","吐着吐着就吐习惯了","被什么恶心到了？"],
        fear : ["您刚刚从恐惧森林里出来？","莫怕莫怕，常来拍拍","刚看了恐怖故事吗？"]
    };

    function selectTask()
    {
        $("#myemotionnotes").remove();
        $(".task-detail").removeClass("taskselected");
        $(event.currentTarget).addClass("taskselected");
        $(".task-detail").children("div").children(".taskcheckbox").removeClass("taskcheckboxon").addClass("taskcheckboxoff");
        $(event.currentTarget).children("div").children(".taskcheckbox").removeClass("taskcheckboxoff").addClass("taskcheckboxon");

        //$(event.currentTarget).after('<textarea id="myemotionnotes" class="emotionnotesinput" placeholder="留下你的心情日记" rows="3"></textarea>');
    }

    function askForHelp()
    {

        var selectedTask =($(".taskselected").children(".task-desc").lengh()>0)? $(".taskselected").children(".task-desc").html():"";
        var postData = "selectedtask="+selectedTask;
        $.ajax({
                url:'askforhelp/'+${scanningResult.id},
                type:"POST",
                data: postData,
                timeout :30000,
                success : function(response){
                              alert("OK");

                          },
                error : function() {
                            alert( "服务器正郁闷，无法记录你的心情日记" );
                        },
                beforeSend:function()
                {
                    $("#inprogress").css("top",$(window).height()/2-50);
                    $("#inprogress").css("left",$(window).width()/2-50);
                    $("#inprogress").css("display","");
                },
                complete:function()
                {
                    $("#inprogress").css("display","none");
                },
                dataType:"json"


            });
    }

    var expressionMessage = getExpressionDescription();
    $("#expressiondescription").html(expressionMessage);


</script>
</html>