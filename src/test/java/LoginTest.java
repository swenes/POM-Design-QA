import Base.BaseTest;
import Pages.LoginPage;
import Pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
@Feature("Login Test Senaryoları")
public class LoginTest extends BaseTest {

    LoginPage login = new LoginPage();
    MainPage mainPage = new MainPage();

    @Test(description = "Başarılı Kullanıcı Girişi")
    public void LoginSuccessful() throws InterruptedException {

        login.fillMail(mail)
                .fillPassword(password)
                .clickLoginButton();
        sleep(3000);
        String value = mainPage.getAccountName();
        assertEquals("Hesabım", value);

    }

    @Test(description = "Başarısız Kullanıcı Girişi")
    public void LoginUnsuccessful() throws InterruptedException {

        login.fillMail(mail)
                .fillPassword("abc")
                .clickLoginButton();
        sleep(3000);
        String value = login.getErrorMessage();
        assertEquals("E-posta adresiniz ve/veya şifreniz hatalı.", value);

    }

    @Test(description = "Boş Karakter Kontrolü")
    public void RequiredControl() throws InterruptedException {

        login.clickLoginButton();
        sleep(3000);
        String value = login.getErrorMessage();
        assertEquals("Lütfen geçerli bir e-posta adresi giriniz.", value);
        login.fillMail(mail)
                .clickLoginButton();
        String value2 = login.getErrorMessage();
        assertEquals("Lütfen şifrenizi giriniz.", value2);
        login.fillPassword(password)
                .clickLoginButton();
        sleep(5000);
        String value3 = mainPage.getAccountName();
        assertEquals("Hesabım", value3);
    }

    @Test(description = "Min Max Karakter Kontrolü")
    public void MinMaxCharacterControl() throws InterruptedException {

        login.fillMail("a")
                .fillPassword("abc")
                .clickLoginButton();
        sleep(3000);
        String value = login.getErrorMessage();
        assertEquals("Lütfen geçerli bir e-posta adresi giriniz.", value);
        login.fillMail(mail)
                .clickLoginButton();

        sleep(3000);
        String value2 = login.getErrorMessage();
        assertEquals("E-posta adresiniz ve/veya şifreniz hatalı.", value2);
        login.fillPassword(password)
                .clickLoginButton();
        sleep(5000);
        String value3 = mainPage.getAccountName();
        assertEquals("Hesabım", value3);
    }

    @Test(description = "Başarısız Kullanıcı Girişi2")
    public void LoginUnsuccessful2() throws InterruptedException {

        login.fillMail(mail)
                .fillPassword("123")
                .clickLoginButton();
        sleep(3000);
        String value = mainPage.getAccountName();
        assertEquals("Giriş Yap", value);

    }

}
