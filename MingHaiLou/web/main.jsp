
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>名海楼</title>
    <link rel="stylesheet" href="./style.css" /> <!-- 导入css样式-->
    <!-- <link rel="stylesheet" href="..//signin.html"> -->
</head>

<body >
    <div class="content_class">
        <header>
            <!-- header:文档的页眉显示-->
            <nav>
                <!--nav:标签定义导航链接的部分-->
                <ul>
                    <!--ul:定义无序列表-->
                    <li><a href="#">首页</a></li> <!-- li:列数 <a> 标签的 href 属性用于指定超链接目标的 URL-->
                    <li><a href="#">美食系列</a></li>
                    <li><a href="#">公司简介</a></li>
                    <li><a href="../人物介绍/index.html">厨师力量</a></li>
                    <li><a href="#">关于我们</a></li>
                </ul>
            </nav>
            <hr /> <!-- hr加一个横线-->

            <div id="head_text">
                <p>"名海楼"</p>
                <input type="button" value="登录购餐" onclick="window.open('<%=request.getContextPath()%>/exemptServlet','_self')" />
                <!-- 注意单双引号的交替使用,_self跳转的是当前页面的窗口-->
            </div>
            <!------------------------------------------------------------->
            <div id="head_icon">
                <div class="icon"><img src="./image/con1.jpg" />
                    <p>用户支持</p>
                </div>
                <div class="icon"><img src="./image/con2.jpg" />
                    <p>品质保证</p>
                </div>
                <div class="icon"><img src="./image/con3.jpg" />
                    <p>精致搭配</p>
                </div>
                <div class="icon">
                    <img src="./image/con4.jpg" />
                    <p onclick="window.open('../枫叶动画/index.html')">快递包邮</p>
                </div>
            </div>
        </header>
        <!------------------------------------------------------------->
        <article>
            <!--<article> 标签定义外部的内容。
            外部内容可以是来自一个外部的新闻提供者的一篇新的文章，或者来自 blog 的文本，或者是来自论坛的文本。亦或是来自其他外部源内容。
            -->
            <div id="content1">
                <div id="content1_img">
                    <div class="content">
                        <p>ENJOY YOUR LIFE</p>
                        <p>享受生活，愉悦自我</p>
                    </div>
                    <div class="img"></div>
                </div>
                <div id="content1_text">
                    <p>ENJOY LIFE AND BE HAPPY</p><br>
                    <h4>品牌故事</h4>
                    <p>Brand story</p>
                    <hr />
                    <p>我们是森林中的动物，寻找栖身之所，寻找同伴，寻找爱</p><br />
                    <input type="button" value="了解详情" />
                    <p>新鲜食材&nbsp;/&nbsp;厨艺大师&nbsp;/&nbsp;烹饪技巧&nbsp;/&nbsp;故事</p>
                </div>
            </div>
            <!------------------------------------------------------------->
            <div id="content2">
                <div class="content">
                    <h5>Canteen &nbsp; show</h5>
                    <p>餐厅展示</p>
                </div>
                <div class="img"></div>
            </div>
            <!------------------------------------------------------------->
            <div id="content3">
                <div id="comtent3_imgs">
                    <div class="content3_img"></div>
                    <div class="content3_img"></div>
                    <div class="content3_img"></div>
                    <div class="content3_img"></div>
                    <div class="content3_img"></div>
<!--
 <div id="content3_img"></div>
                    <div id="content3_img"></div>
                    <div id="content3_img"></div>
                    <div id="content3_img"></div>
                    <div id="content3_img"></div>-->
                </div>
            </div>
            <!------------------------------------------------------------->
            <div id="content4">
                <div class="content">
                    <h5><b>Superior private room</b></h5>
                    <p>高级包间</p>
                </div>
                <div class="img">
                    <img src="./image/Canteen2.jpeg" />
                </div>
            </div>
            <!------------------------------------------------------------->
            <div id="content5">
                <div id="content">
                    <h5>Special Dishes</h5>
                    <p>特色菜品</p>
                </div>
                <div class="img">
                    <div class="content5_img"></div>
                    <div class="content5_img"></div>
                    <div class="content5_img"></div>
                </div>
                <div class="content">
                    <div class="content5_content">
                        <h5><b>宁都肉丸</b></h5>
                        <p>宁都肉丸，分年过节必备食品，主要成为是肉和淀粉。弹劲十足
                        </p>
                        <input type="button" value="了解更多" />
                    </div>
                    <div class="content5_content">
                        <h5><b>南昌瓦罐汤</b></h5>
                        <p>南昌瓦罐汤，南昌特色美食，将煲汤的食材放入瓦罐内，可以让美食的味道，充分保留，体验原汁原味的瓦罐汤</p>
                        <input type="button" value="了解更多" />
                    </div>
                    <div class="content5_content">
                        <h5><b>潮汐海鲜</b></h5>
                        <p>因潮汕地区海岸线曲折绵长，多优良港湾，海产异常丰富，潮菜以烹饪海鲜见长，甜菜荤制各具特色。。</p>
                        <input type="button" value="了解更多" />
                    </div>
                </div>
            </div>
        </article>
        <!------------------------------------------------------------->
        <footer>
            <!--<footer> 标签定义 section 或 document 的页脚。-->
            <div id="footer1">
                <p> 190-3829-8009 </p>
                <p>27912213258@qq.com</p>
            </div>
            <div id="footer2">
                <div id="footer_a">
                    <a herf="#">首页</a>
                    <a herf="#">品牌故事</a>
                    <a href="#">人才招聘</a>
                    <a href="#">新闻资讯</a>
                </div>
                <div class="order">
                    <span class="line"></span>
                    <span class="txt">工作时间: 周一至周五9:00-18:00</span>
                    <span class="line"></span>
                </div>
            </div>
            <div id="footer3">
                <div class="icon"><img src="./image/con1.jpg" /></div>
                <div class="icon"><img src="./image/con2.jpg" /></div>
                <div class="icon"><img src="./image/con3.jpg" /></div>
                <div class="icon"><img src="./image/con4.jpg" /></div>
            </div>
        </footer>
    </div>
</body>

</html>