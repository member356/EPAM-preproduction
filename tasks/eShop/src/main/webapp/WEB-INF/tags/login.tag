<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-danger">
            <div class="panel-body">
                <form action="/eShop/login" method=post>
                    <br>
                    <div class="row">
                        <div class="col-lg-3" align="center">Username</div>
                        <div class="col-lg-6"><input type="text" name="login" id="login"></div>
                    </div>
                    <div class="errormsg">${loginerror}</div>
                    <br>
                    <div class="row">
                        <div class="col-lg-3" align="center">Password</div>
                        <div class="col-lg-6"><input type="password" name="password"></div>
                    </div>
                    <div class="errormsg">${passworderror}</div>
                    <br>
                    <br>
                    <div align="center">
                        <button type="submit" class="btn btn-lg btn-outline-primary">Submit</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>