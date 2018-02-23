<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jspf" %>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/navbar.jspf" %>
<div class="guarantee" align="center">
    <h3><b>Find your next apartment!</b></h3>
    <br>
    <div class="row">
        <div class="offertext"><p class="unicodeglygh">&#127968;</p></div>
        <p>We will offer you the best real estate</p>
    </div>
</div>
<c:choose>
	<c:when test="${empty sessionScope.user}">
	    <div class="btn-group btn-group-justified" align="center">
            <form action="/eShop/signup">
                <button type="submit" class="btn btn-default">Registration</button>
            </form>
        </div>
		<br>
		<tag:login />
	</c:when>
	<c:otherwise>
        <div class="btn-group btn-group-justified" align="center">
            <form action="/eShop/items">
                <button type="submit" class="btn btn-default">Items List</button>
            </form>
         </div>
	</c:otherwise>
</c:choose>
    <div class="guarantee" align="center">  
        <p align="center">
        <h3><b>We guarantee</b></h3>
        <br>
        <div class="row">
            <div class="col-sm-4">
                <div class="offertext"><p class="unicodeglygh">&#8633;</p></div>
                <h4><b>QUICKNESS</b></h4>
                <p>Our best searching algorithms find the apartments which will suit to you the most</p>
            </div>
            <div class="col-sm-4">
                <div class="offertext"><p class="unicodeglygh">&#36;</p></div>
                <h4><b>ECONOMIZE</b></h4>
                <p>Buyer-friendly prices and many discounts for you</p>
            </div>
            <div class="col-sm-4">
                <div class="offertext"><p class="unicodeglygh">&#10003;</p></div>
                <h4><b>SAFETY</b></h4>
                <p>Law-safety buying and transparency relationships between us</p>
            </div>
        </div>
    </div>
</body>
</html>