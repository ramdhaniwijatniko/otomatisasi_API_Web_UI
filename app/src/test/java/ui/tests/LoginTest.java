package ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Gunakan WebDriverManager untuk mengelola ChromeDriver secara otomatis
        WebDriverManager.chromedriver().avoidShutdownHook().setup();

        // Konfigurasi Chrome agar berjalan di lingkungan CI/CD
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Tambahkan mode headless
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginSuccess() {
        driver.get("https://www.saucedemo.com/");

        // Masukkan username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        // Masukkan password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        // Klik tombol login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Verifikasi bahwa login berhasil (cek URL setelah login)
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Login gagal!");
    }

    @AfterEach
    public void tearDown() {
        // Tutup browser setelah pengujian selesai
        if (driver != null) {
            driver.quit();
        }
    }
}
