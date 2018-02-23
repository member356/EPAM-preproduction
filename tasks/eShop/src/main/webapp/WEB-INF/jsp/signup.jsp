<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/navbar.jspf" %>

<div class="modal-header" align="center">
    <h4>Sign up form</h4>
</div>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-danger">
            <div class="panel-body">
                <form id="submitform" action="/eShop/signup" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-md-4" align="right">
                            Login
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="login" id="loginid" value=${loginvalue}></input>
                        </div>
                    </div>
                    <div id="loginerror" class="errormsg">${loginerror}</div>
                    <br>
                    <div class="row">
                        <div class="col-md-4" align="right">
                            Password
                        </div>
                        <div class="col-md-8">
                            <input type="password" name="password" id="passwordid"/>
                        </div>
                    </div>
                    <div id="passworderror" class="errormsg">${passworderror}</div>
                    <br>
                    <div class="row">
                        <div class="col-md-4" align="right">
                            Email
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="email" id="emailid" value=${emailvalue}></input>
                        </div>
                    </div>
                    <div id="emailerror" class="errormsg">${emailerror}</div>
                    <br>
                    <div class="row">
                        <div class="col-md-4" align="right">
                            First name
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="firstname" id="firstnameid" value=${firstnamevalue}></input>
                        </div>
                    </div>
                    <div id="firstnameerror" class="errormsg">${firstnameerror}</div>
                    <br>
                    <div class="row">
                        <div class="col-md-4" align="right">
                            Last name
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="lastname" id="lastnameid" value=${lastnamevalue}></input>
                        </div>
                    </div>
                    <div id="lastnameerror" class="errormsg">${lastnameerror}</div>
                    <br>
                    <div class="error">
                        <c:out value="${map.captcha}"/>
                    </div>
                    <div>
                        <tag:captcha />
                        <input name="captchaValue" type="text" size="10" id="captchaValue" maxlength="20">
                    </div>
                    <div class="errormsg">${captchaerror}</div>
                    <br>
                    <div class="row">
                        <div class="col-md-3" align="right">
                            Check the subscriptions
                        </div>
                        <div class="col-md-3">
                            <input type="checkbox" name="subscription" value="prices" checked>Prices</input>
                        </div>
                        <div class="col-md-3">
                            <input type="checkbox" name="subscription" value="discounts">Discounts</input>
                        </div>
						<div class="col-md-3">
                            <input type="checkbox" name="subscription" value="offers">Special offers</input>
                        </div>
                    </div>
                    <div class="row">
                        <span><input name="file" id="file" type="file"></span>
                    </div>
                    <div align="center">
                        <button type="submit" class="btn btn-default" id="btnjs">Submit with JS validation</button>
                    </div>
                    <br>
                    <div align="center">
                        <button type="submit" class="btn btn-default" id="btnjquery">Submit with JQuery validation
                        </button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>