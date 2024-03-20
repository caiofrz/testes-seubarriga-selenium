package com.caio_ikaro.seubarriga.contas;

import com.caio_ikaro.seubarriga.BaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContaTest {

  WebDriver driver;

  @BeforeEach
  void setup() throws InterruptedException {
    driver = new ChromeDriver();
    driver.get(BaseProperties.BASE_URL);

    WebElement email = driver.findElement(By.id("email"));
    email.sendKeys("teste999999999999999999@email.com");
    WebElement senha = driver.findElement(By.id("senha"));
    senha.sendKeys(BaseProperties.PASSWORD);
    driver.findElement(By.cssSelector("[type='submit']")).click();
    Thread.sleep(1000);
  }

  @Test
  void deveRetornarErroAoCriarUmaContaComNomeJaCadastrado() {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/addConta']")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys(BaseProperties.NOME_CONTA);

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Já existe uma conta com esse nome!";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarCriarUmaContaSemNome() {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/addConta']")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys("");

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Informe o nome da conta";

    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarCriarUmaContaComSomenteEspacosEmBrancoNoNome() {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/addConta']")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.sendKeys("  ");

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Informe o nome da conta";

    Assertions.assertNotNull(error);
    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarEditarUmaContaComSomenteEspacosEmBrancoNoNome() throws InterruptedException {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/contas']")).click();

    driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/a[1]")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.clear();
    nome.sendKeys("  ");

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Informe o nome da conta";

    Assertions.assertNotNull(error);
    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarEditarUmaContaSemNome() {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/contas']")).click();

    driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/a[1]")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.clear();
    nome.sendKeys("");

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Informe o nome da conta";

    Assertions.assertNotNull(error);
    Assertions.assertEquals(errorMessageExpected, error.getText());
  }

  @Test
  void deveRetornarErroAoTentarEditarUmaContaComNomeJaCadastrado() {
    WebElement menuContas = driver.findElement(By.className("dropdown-toggle"));
    menuContas.click();
    driver.findElement(By.cssSelector("[href='/contas']")).click();

    driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]/a[1]")).click();

    WebElement nome = driver.findElement(By.id("nome"));
    nome.clear();
    nome.sendKeys(BaseProperties.NOME_CONTA);

    driver.findElement(By.cssSelector("[type='submit']")).click();

    WebElement error = driver.findElement(By.className("alert-danger"));

    String errorMessageExpected = "Já existe uma conta com esse nome!";

    Assertions.assertNotNull(error);
    Assertions.assertEquals(errorMessageExpected, error.getText());
  }
}
