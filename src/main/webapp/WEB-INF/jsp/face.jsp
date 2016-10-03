<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>情绪检测</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/tribeofspirit.css?ver=0.2" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
        var jsApiList = [
            'checkJsApi',
            'chooseImage',
            'uploadImage'
        ];
        $(document).ready(function () {
            initPage();
        });
        function initPage() {
            $.post("wxConfig?_method=get", {"url": window.location.href}, function (data, status) {
                data.debug = false;
                data.jsApiList = jsApiList;
                wx.config(data);
            });
        }

        function setToDetectPhotoClip()
        {

            $("#toDetectPhoto").css("clip","rect(0px,"+$(window).width()+"px,"+$(window).height()+"px,0px)");

            //$("#camera").attr("coords",cameraCoords);
        }

        wx.ready(function () {
            // 5 图片接口
            // 5.1 拍照、本地选图
            var images = {
                localId: [],
                serverId: []
            };

            setToDetectPhotoClip();

            $('#startscanning').click(photoshot);
            //$("#retry").click(photoshot);

            //$('#selectphoto').click(pickupPhoto);

            function photoshot() {
                 wx.chooseImage({
                     count:1,
                     sourceType: ['camera','album'],
                     success: function (res) {
                         images.localId = res.localIds;
                         if (images.localId != null && images.localId.length > 0) {
                             //document.querySelector('#uploadImage').removeAttribute("disabled");
                             scanningReady(images.localId[0]);
                             uploadImage();
                         }
                     }
                 });
            };

            /*function pickupPhoto() {
                wx.chooseImage({
                    count:1,
                    sourceType: ['album'],
                    success: function (res) {
                        images.localId = res.localIds;
                        if (images.localId != null && images.localId.length > 0) {
                            //document.querySelector('#uploadImage').removeAttribute("disabled");
                            scanningReady(images.localId[0]);

                            uploadImage();
                        }
                    }
                });
            }*/

            function uploadImage() {
                $("#startscanning").unbind("click");
                wx.uploadImage({
                    localId: images.localId[0],
                    success: function (res) {
                        //location.href = "detect?mediaId=" + res.serverId;
                        //$("#startscanning").bind("click",function(){startToScan(res.serverId)});
                        startToScan(res.serverId);
                    },
                    fail: function (res) {
                        alert(JSON.stringify(res));
                    }
                });
            }

        });
        wx.error(function (res) {
            alert(res.errMsg);
        });

        function scanningReady(srcUrl)
        {
            $("#buttonbar").remove();
            //$("#buttonbar").addClass("footer");
            //$("#buttonbar").html('<p style="margin-top:20px; font-size:x-large; font-color:#FFFFFF">扫描中......</p>');
            $("#toDetectPhoto").attr("src",srcUrl);
            //$("#toDetectPhoto").css("max-width","");
            $("#toDetectPhoto").css("width","100%");
            $("#toDetectPhoto").css("height","100%");
            setToDetectPhotoClip();
            //$("#buttonbar").css("display","none");
            //$("#buttonbarafteruploading").css("display","");
            //$("#home").remove();
            //$("#photoSelected").css("display","");
            //$("<img id=\"toDetectPhoto\" style=\"max-width:100%;\"  src=\""+srcUrl+"\">").appendTo("#photoPlaceHolder");

        }

        function startToScan(mediaId){
            //alert(mediaId);
            $.ajax({
                url:'detect/'+mediaId,
                type:"POST",
                timeout :30000,
                success : function(response){
                              var result = jQuery.parseJSON(response);
                             if(result != null && result.scanningId!='undefined'){
                                  location.href = "result?scanningid="+result.scanningId;
                             }
                             else
                             {
                                  alert( "由于颜值太高，导致计算爆表，请再试一次吧!" );
                             }

                          },
                error : function() {
                            alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
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

            /*$.post('detect/'+mediaId, {},
                        function(response, status){
                            var result = jQuery.parseJSON(response);
                           if(status == 'success' && result != null){
                                location.href = "result?scanningid="+result.scanningId;
                           }
                           else
                           {
                                alert( "由于颜值太高，导致计算爆表，请再试一次吧!" );
                           }

                        }, "json").fail(function() {
                            alert( "由于颜值太高，导致计算爆表，请再试一次吧。" );
                    });*/
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="offset-col-md-1 col-md-18 offset-col-sm-1 col-sm-18 offset-col-sd-1 col-xs-18">
            <img id="toDetectPhoto" style="max-width:100%;position:absolute;z-index: -1" src="images/loading.jpg">
        </div>
    </div>
    <div id="inprogress" class="inprogress">
        <img src="images/loadingicon.png" class="inprogressimage">
    </div>

</div>
<div id="buttonbar" class="row buttonbar" >
    <p style="margin-top: 27px;">
    <img id="startscanning" src="images/icon_action.png" style="height: 45px;
            display: block;
            margin-left: auto;
            margin-right: auto;">
    </p>

</div>
<!--
<div id="buttonbarafteruploading" class="row" style="display:none;background-color: rgba(0,0,0,0.2);height: 100px;position: fixed;bottom:0;width: 100%;margin:0">
    <p style="margin-top: 27px;">
    <div class="col-md-4 col-sm-4 col-xs-4">
        <img id="retry" src="images/shotagain.png" style="height: 45px;
            display: block;
            margin-left: auto;
            margin-right: auto;"></div>
    <div class="col-md-4 col-sm-4 col-xs-4">

        <img id="startscanning" src="images/action.png" style="height: 45px;
            display: block;
            margin-left: auto;
            margin-right: auto;">
    </div>
    <div class="col-md-6 col-sm-6 col-xs-6"></div>
    </p>

</div>-->
</body>
</html>