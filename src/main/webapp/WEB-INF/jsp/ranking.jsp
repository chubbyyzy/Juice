<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>情绪排名</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/tribeofspirit.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>

    <style>


        .ranking-bg{
            height: 180px;
            background: no-repeat fixed top;
            background-size: contain;
            text-align: center;
            color: white;
            font-size: small;
        }

        .ranking-title{
            position:relative;
            top:25px;
        }

        .ranking-list{
            background-color:#FFFFFF;
            opacity: 0.8;
            font-color:black;
        }

        .ranking-desc{
            color:gray;
            size: 10px;
        }

        .like-number{
            color:gray;
            text-align:center;
        }

        .heart{
            text-align:center;
        }

        .user-result{
            height:120px;
            background-color:#ffce00;
        }

        .line-seperator-user{
            height:20px;
            background-color:#ebebeb;
        }

        .line-seperator-friends{
            height:5px;
            background-color:#ebebeb;
        }


    </style>

</head>
<body>

<div class="container">

    <div class="row">

        <div class="ranking-bg" id="ranking-bg">

        </div>
    </div>
</div>

<div class="container ranking-list">
    <div class="row">

                <div class="row user-result">

                    <div class="row">
                        <div class="col-xs-2 col-sm-2 col-md-2">
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2">
                            <img class="img-rounded" src="docstorage/${userResult.imageName}" height="120px" width="80px"/>
                        </div>
                        <div class="col-xs-4 col-sm-4 col-md-4">
                        </div>

                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <h4>${user.nickName}</h4>

                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2">
                            <h4>${userResult.moodScore}</h4>
                        </div>
                        <div class="col-xs-4 col-sm-4 col-md-4">
                            <div class="like-number">${userResult.likeNumber}</div>
                            <div class="heart"><img src="images/heart1.png" height="30px;" class="like"></div>
                        </div>

                    </div>
                </div>
                <div class="row line-seperator-user">
                </div>

                <c:forEach var="rakingResult" items="${rankingResults}" varStatus="ranking">
                    <div class="row">
                        <div class="col-xs-1 col-sm-1 col-md-1">
                        </div>
                        <div class="col-xs-1 col-sm-1 col-md-1">
                            <h3>${ranking.count}</h3>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2">
                            <img class="img-rounded" src="docstorage/${rakingResult.image_name}" height="120px" width="80px"/>
                        </div>
                        <div class="col-xs-4 col-sm-4 col-md-4">
                        </div>

                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <h4>${rakingResult.nick_name}</h4>

                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2">
                            <h4>${rakingResult.mood_score}</h4>
                        </div>
                        <div class="col-xs-4 col-sm-4 col-md-4">
                            <div class="like-number">10</div>
                            <div class="heart"><img src="images/heart1.png" height="30px;" class="like"></div>
                        </div>

                    </div>
                    <div class="row line-seperator-friends">
                    </div>

            </c:forEach>


    </div>
</div>
<script>
    $(document).ready(function () {
        $("#ranking-bg").css({"background-image":"url(${user.photoUri})"});

        $(".like").click(function(){
            $(this).attr("src", "images/heart2.png");
            var likeNumber = $(this).parent().prev().html();
            $(this).parent().prev().html(likeNumber.parseInt() + 1 );

        });


    });

</script>

</body>
</html>