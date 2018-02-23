<html>
<head>
    <%@ include file="/WEB-INF/jsp/head.jspf" %>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/navbar.jspf" %>
    <br>
    <div class="container">
        <div class="btn-group">
             <a href="/eShop">
                 <button type="button" class="btn btn-default">
                     Go back to home page
                 </button>
             </a>
         </div>
    </div>
    <div class="row">
        <div class="col-md-2 col-md-offset-5">
            <h4 class="modal-header">Error</h4>
            <h2 class="error">${message}</h4>
        </div>
    </div>
</body>
</html>