package com.caio_ikaro.seubarriga.auth;

import com.caio_ikaro.seubarriga.BaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroUsuarioTest {

  WebDriver driver;

  @BeforeEach
  void setup() {
    driver = new ChromeDriver();
    driver.get(BaseProperties.BASE_URL + "/cadastro");
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioJaCadastrado() {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys(BaseProperties.USERNAME);
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Endereço de email já utilizado";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioComEmailInvalido() throws InterruptedException {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys(BaseProperties.USERNAME);
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.INAVLID_EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    Thread.sleep(2000);
    //Se fosse um login com sucesso deveria ser redirecionado para /logar
    Assertions.assertNotEquals(BaseProperties.BASE_URL + "/logar", driver.getCurrentUrl());
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioSemEmail() {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys(BaseProperties.USERNAME);
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys("");
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Email é um campo obrigatório";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioSemSenha() {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys(BaseProperties.USERNAME);
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys("");

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Senha é um campo obrigatório";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioSemNome() {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys("");
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Nome é um campo obrigatório";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoCriarUmUsuarioComSomenteEspaçosEmBrancoNoNome() {
    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys("    ");
    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement cadastrar = driver.findElement(By.cssSelector("[type='submit']"));
    cadastrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    Assertions.assertNotNull(error);
  }

}
