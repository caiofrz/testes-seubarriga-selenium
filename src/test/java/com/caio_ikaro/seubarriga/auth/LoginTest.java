package com.caio_ikaro.seubarriga.auth;

import com.caio_ikaro.seubarriga.BaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

  WebDriver driver;

  @BeforeEach
  void setup() {
    driver = new ChromeDriver();
    driver.get(BaseProperties.BASE_URL + "/login");
  }

  @Test
  void deveRetornarErroAoLogarUmUsuarioNaoCadastrado() {

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys("teste9999999999999999990@email.com");
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement entrar = driver.findElement(By.cssSelector("[type='submit']"));
    entrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Problemas com o login do usuário";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarLogarSemEmail() {

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys("");
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement entrar = driver.findElement(By.cssSelector("[type='submit']"));
    entrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Email é um campo obrigatório";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarLogarSemSenha() {

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys("");

    WebElement entrar = driver.findElement(By.cssSelector("[type='submit']"));
    entrar.click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Senha é um campo obrigatório";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarLogarComEmailInvalido() throws InterruptedException {

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys(BaseProperties.INAVLID_EMAIL);
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement entrar = driver.findElement(By.cssSelector("[type='submit']"));
    entrar.click();

    Thread.sleep(2000);
    //Se fosse um login com sucesso deveria ser redirecionado para /logar
    Assertions.assertNotEquals(BaseProperties.BASE_URL + "/logar", driver.getCurrentUrl());
  }

  @Test
  void deveRealizarLogoutComSucesso() throws InterruptedException {

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys("teste999999999999999999@email.com");
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);

    WebElement entrar = driver.findElement(By.cssSelector("[type='submit']"));
    entrar.click();

    Thread.sleep(1000);
    WebElement sair = driver.findElement(By.cssSelector("[href='/logout']"));
    sair.click();

    WebElement login = driver.findElement(By.cssSelector("[href='/login']"));
    Assertions.assertNotNull(login);
    Assertions.assertEquals(BaseProperties.BASE_URL + "/logout", driver.getCurrentUrl());
  }
}
