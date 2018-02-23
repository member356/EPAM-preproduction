<c:if test="${applicationScope.captchaType == 'field'}">
<input type="hidden" name="encryptCaptcha" value="${encryptCaptcha}"/>
</c:if>
<img src="captcha?encryptCaptcha=${encryptCaptcha}" border="0"/>