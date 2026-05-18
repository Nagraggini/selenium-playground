<!-- 1. Státusz és Minőség -->
[![Java Selenium CI](https://github.com/Nagraggini/selenium-playground/actions/workflows/ci.yml/badge.svg)](https://github.com/Nagraggini/selenium-playground/actions/workflows/ci.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/521e2b8a4c4a4b39afd7bc3324f370d5)](https://app.codacy.com/gh/Nagraggini/selenium-playground/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Nagraggini_selenium-playground&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Nagraggini_selenium-playground)

<!-- 2. Teszt adatok -->
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

<!-- 3. Technológia és Eszközök -->
![Top Language](https://img.shields.io/github/languages/top/Nagraggini/selenium-playground)
![Selenium WebDriver](https://img.shields.io/badge/Selenium-WebDriver-333333?style=flat&logo=selenium&logoColor=43B02A)

<!-- 4. Egyéb -->
![License](https://img.shields.io/badge/license-MIT-green)

**Pipeline:** Push → GitHub Actions:

checkout
↓
setup java
↓
maven build
↓
selenium tests
↓
jacoco coverage
↓
sonar analysis

[Programozásról szóló könyvem](https://nagraggini.github.io/my-awesome-book/)

**Tartalomjegyzék**

- [Források](#források)
- [Seleniumról általánosságban](#seleniumról-általánosságban)
  - [Selenium](#selenium)
    - [Selenium IDE](#selenium-ide)
    - [Selenium WebDriver](#selenium-webdriver)
    - [Selenium Grid](#selenium-grid)
- [Előkészületek](#előkészületek)
  - [Jelvények engedélyezése](#jelvények-engedélyezése)
- [Első tesz](#első-tesz)
- [GitHub Actions](#github-actions)
- [SonarCloud beállítása](#sonarcloud-beállítása)
- [Codacy beállítása](#codacy-beállítása)
- [Weboldalak, amiket lehet tesztelni](#weboldalak-amiket-lehet-tesztelni)
- [Selenium WebDriver + Java Cheat Sheet](#selenium-webdriver--java-cheat-sheet)
  - [1. Böngésző inicializálása és beállítások](#1-böngésző-inicializálása-és-beállítások)
  - [2. Navigáció és ablakkezelés](#2-navigáció-és-ablakkezelés)
  - [3. Elemkeresési stratégiák (By lokátorok)](#3-elemkeresési-stratégiák-by-lokátorok)
  - [4. Elemekkel való interakció (WebElement)](#4-elemekkel-való-interakció-webelement)
  - [5. Információ lekérése a böngészőtől](#5-információ-lekérése-a-böngészőtől)
  - [6. Várakozások (Waits) – A stabilitásért](#6-várakozások-waits--a-stabilitásért)
    - [Explicit Wait (Ajánlott)](#explicit-wait-ajánlott)
    - [Implicit Wait](#implicit-wait)
  - [7. Speciális elemek kezelése](#7-speciális-elemek-kezelése)
    - [Dropdown (Legördülő menü) kezelése](#dropdown-legördülő-menü-kezelése)
    - [Alert-ek (Felugró ablakok) kezelése](#alert-ek-felugró-ablakok-kezelése)
    - [Iframes (Beágyazott oldalak) kezelése](#iframes-beágyazott-oldalak-kezelése)
- [AssertJ](#assertj)
- [Act](#act)
- [Local teszthez és githez puska](#local-teszthez-és-githez-puska)

# Források

https://www.youtube.com/watch?v=HpL6EX2kjq4
Gemini and ChatGPT

# Seleniumról általánosságban

## Selenium

A Selenium egy széles körben elterjedt, nyílt forráskódú tesztautomatizálási keretrendszer, amelyet főként webes alkalmazások felhasználói felületének (UI) tesztelésére használnak.

### Selenium IDE

A Selenium IDE egy böngészőbe épülő kiegészítő, amely a legegyszerűbb automatizálási megoldások közé tartozik.
A működése a „record & playback” elven alapul, vagyis a felhasználó által manuálisan végrehajtott lépéseket rögzíti, majd később ezeket automatikusan képes újrajátszani.
Használatához nem feltétlenül szükséges programozási ismeret, azonban szkriptalapú nyelveket is támogat.
Hátránya, hogy több korlátozással rendelkezik, így összetettebb tesztelésre kevésbé alkalmas.

### Selenium WebDriver

A Selenium WebDriver egy programozás alapú megoldás UI tesztek automatizálására.
Egy olyan API-t biztosít, amely lehetővé teszi, hogy különböző programozási nyelveken (pl. Java, C#, Python) írjunk teszteket, amelyek böngészőket vezérelnek.
Közvetlen kapcsolatot létesít a böngészővel, így gyors és rugalmas végrehajtást biztosít.
Széles körben elfogadott ipari szabványnak számít, és a legtöbb modern böngészővel kompatibilis.

### Selenium Grid

A Selenium Grid egy olyan infrastruktúra, amely lehetővé teszi a tesztek párhuzamos futtatását különböző környezetekben.
Segítségével a tesztek több gépen és több böngészőn egyszerre hajthatók végre, ami jelentősen felgyorsítja a tesztelési folyamatot és támogatja a skálázhatóságot.

# Előkészületek

VS Code kiegészőt töltsd le: Extension Pack for Java

Ctrl+Shift+P -> Írd be: Java: Create Java Project. -> Válaszd a Maven opciót, majd az maven-archetype-quickstart-ot. -> Nevezd el a projektet. Majd nyomj pár entert a terminálba. 

Terminálba -> git init

A .gitignore fájlt hozd létre és írd bele, azt ami ennél a projektnél szerepel.

Töltd fel a github.com-ra.

A pom.xml-ben a dependencies részre ezt másold be:
```xml
<!-- Source: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
		<dependency>
		    <groupId>org.seleniumhq.selenium</groupId>
		    <artifactId>selenium-java</artifactId>
		    <version>4.41.0</version>		    
		</dependency>
		
		<!-- Source: https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
		<dependency>
		    <groupId>io.github.bonigarcia</groupId>
		    <artifactId>webdrivermanager</artifactId>
		    <version>6.3.3</version>
		    <scope>compile</scope>
		</dependency>
		
		
		<!-- Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter -->
		<dependency>
		    <groupId>org.junit.jupiter</groupId>
		    <artifactId>junit-jupiter</artifactId>
		    <version>5.12.2</version>
		    <scope>test</scope>
		</dependency>

         <!--Modern AssertJ használatához.-->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.27.3</version>
            <scope>test</scope>
        </dependency>
```

Ezt töröld belőle (JUnit 4):
```xml
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
```

maven-surefire-plugin-t cseréld le újabbra:
```xml
    <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.13.0</version>

        <configuration>
                <source>21</source>
                <target>21</target>
        </configuration>
    </plugin>

<!--És ezt is töröld: -->
    <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
    </plugin>
```

A https://mvnrepository.com/-ról vannak a függőségek:
- Selenium Java 
- WebDriverManager (bonigarcia)
- JUnit Jupiter (Aggregator)


Valamint az 1.7-et írd át 21-re.
```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

Van még jó pár hasznos kiegészítő beállítva ezen projekt pom.xml-be, javalott kimásolni. 
Ezk vannak benne a setup-ban:
modern Selenium
- JUnit 5
- AssertJ
- Jacoco
- SonarCloud
- GitHub Actions
- Java 21

Utána ellenőrid le, hogy minden jól sikerült-e beállítani, terminálba: 
mvn clean verify

Ha nincsen semmi piros hibaüzenet vagy warning, akkor jó.

Eclipse esetén:

A pom.xml-nél az első sorban ennél a linknél https helyett http legyen. http://maven.apache.org/xsd/maven-4.0.0.xsd

Projekten jobb klikk -> Build Path -> Configure Build Path -> Libraries -> ModulePath -> Jobb szélén Edit -> Java 21 -> Majd bal szélén katt a Java Compiler-re és Java 21.

## Jelvények engedélyezése

Alapértelmezetten a workflow-k csak olvashatják a repódat. Mivel a jelvényeket be kell írniuk a mappádba, írási jogot kell adnod nekik:

Menj a GitHubon a Settings -> Actions -> General fülre.

Görgess le a Workflow permissions részig.

Jelöld be a Read and write permissions opciót, majd kattints a Save gombra.

# Első tesz

A test/java/../.. mappában lévő java fájlban láthatod az első tesztet.

```java

WebDriver driver;

@Test
    public void ElsoTeszt() {
        //Ezzel nem jelenik meg a GUI, mert github actions-ben nincs ilyen, ha hagyod megjelenni, akkor a workflows el fog hasalni.
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");

        // A böngésző indítása
        // Add át az opciókat a driver példányosításakor
        driver = new ChromeDriver(options);

        try {
            // Navigáció egy oldalra
            driver.get("https://www.google.hu");

            // Kiírjuk az oldal címét ellenőrzésképpen
            System.out.println("Az oldal címe: " + driver.getTitle());

        } finally {
            // Mindig zárjuk be a böngészőt a végén!
            driver.quit();
        }
    }
```

# GitHub Actions

A github.com-on -> Actions  -> Java with Maven.

# SonarCloud beállítása

sonarcloud.io-ra regisztrálj és importáld be a projektet.
Utána Choose your Analysis Method: With GitHub Actions -> 
Az oldalon le van írva, hogyan állítsd be a secret token a github-on (Saját repod -> Settingy -> Secrets and Variables -> Actions -> Variables lapfül -> New environment secrets).

A projekted gyökér könyvtárában is csinálj egy .secrets fájl-t, amit hadj ki a .gitignore fájllal a verziókövetésből. Ezt írd bele: SONAR_TOKEN=a_te_sonar_cloud_tokened

Create or update a build file: Maven -> Configure -> És másold be azt a workflows-t,a mit a SonarCloud mutat, viszont a branch nevét (master/main) és a java verzióját javítsd át mindkét helyen.

A Java-version-t írd át arra amit a terminál kiír, amikor ezt írod bele: java -version

-> Commit changes-- gombra katt.

A projektben lévő workflow fájl profibban van megcsinálva ennél.

# Codacy beállítása

codacy.com importáld be a repod. Majd nyisd mega  repod a codacy-n és bal alul beállítások -> Codacy Badge-t másold ki és rakd a REAdME.md fájlod tetejére. 

# Weboldalak, amiket lehet tesztelni

https://automationexercise.com/test_cases
http://www.uitestingplayground.com/
https://the-internet.herokuapp.com/
https://www.saucedemo.com/


# Selenium WebDriver + Java Cheat Sheet

Ez a dokumentum a legfontosabb és leggyakrabban használt Java Selenium WebDriver parancsokat tartalmazza.

---

## 1. Böngésző inicializálása és beállítások

```java
// WebDriver példányosítása (Chrome)
WebDriver driver = new ChromeDriver();

// Böngésző ablak maximalizálása
driver.manage().window().maximize();

// Böngésző ablak teljes képernyős módba tétele (F11)
driver.manage().window().fullscreen();

// Sütik (Cookies) törlése
driver.manage().deleteAllCookies();
```

---

## 2. Navigáció és ablakkezelés

| Parancs                       | Leírás                                                         |
| ----------------------------- | -------------------------------------------------------------- |
| `driver.get("url")`           | Megnyitja a megadott URL-t a böngészőben.                      |
| `driver.navigate().to("url")` | Megnyitja az URL-t, de megőrzi a böngészési előzményeket.      |
| `driver.navigate().back()`    | Visszalép az előző oldalra.                                    |
| `driver.navigate().forward()` | Előrelép a következő oldalra.                                  |
| `driver.navigate().refresh()` | Frissíti (újratölti) az aktuális oldalt.                       |
| `driver.close()`              | Bezárja az aktuálisan fókuszban lévő böngészőfület.            |
| `driver.quit()`               | Bezárja az összes ablakot és teljesen leállítja a WebDriver-t. |

---

## 3. Elemkeresési stratégiák (By lokátorok)

Elemek kereséséhez a `driver.findElement(By...)` vagy `driver.findElements(By...)` metódusokat használjuk.

```java
// ID alapján
WebElement elementById = driver.findElement(By.id("user-name"));

// Class (Osztály) név alapján
WebElement elementByClass = driver.findElement(By.className("btn-submit"));

// Name attribútum alapján
WebElement elementByName = driver.findElement(By.name("email"));

// HTML tag neve alapján
WebElement elementByTag = driver.findElement(By.tagName("h1"));

// Pontos link szöveg alapján
WebElement elementByLink = driver.findElement(By.linkText("Kapcsolat"));

// Részleges link szöveg alapján
WebElement elementByPartialLink = driver.findElement(By.partialLinkText("Kapcs"));

// CSS Selector használatával
WebElement elementByCss = driver.findElement(
    By.cssSelector("div.container > input[type='text']")
);

// XPath használatával
WebElement elementByXpath = driver.findElement(
    By.xpath("//button[@id='submit-btn']")
);
```

---

## 4. Elemekkel való interakció (WebElement)

Miután megtaláltál egy elemet, az alábbi parancsokkal vezérelheted vagy kérhetsz le róla információt.

```java
WebElement element = driver.findElement(By.id("search-box"));

// Interakciók
element.click();                    // Rákattintás
element.sendKeys("Keresendő szó"); // Szöveg beírása
element.clear();                    // Beviteli mező kiürítése
element.submit();                   // Űrlap (form) elküldése

// Információ lekérés az elemtől
String text = element.getText();              // Elem látható szövege
String value = element.getAttribute("value"); // HTML attribútum értéke
String cssValue = element.getCssValue("color"); // CSS tulajdonság értéke

// Állapot ellenőrzések (Boolean: true/false)
boolean isDisplayed = element.isDisplayed(); // Látható az oldalon?
boolean isEnabled = element.isEnabled();     // Aktív / kattintható?
boolean isSelected = element.isSelected();   // Be van jelölve?
```

---

## 5. Információ lekérése a böngészőtől

```java
// Aktuális oldal címe (Title)
String pageTitle = driver.getTitle();

// Aktuális oldal pontos URL címe
String currentUrl = driver.getCurrentUrl();

// Az oldal teljes HTML forráskódja
String pageSource = driver.getPageSource();
```

---

## 6. Várakozások (Waits) – A stabilitásért

A szinkronizációs hibák elkerülése érdekében kötelező várakozásokat használni.

### Explicit Wait (Ajánlott)

Egy adott elemre és konkrét feltételre vár (maximum a megadott ideig).

```java
WebDriverWait wait = new WebDriverWait(
    driver,
    Duration.ofSeconds(10)
);

// Vár, amíg az elem kattinthatóvá nem válik
WebElement gomb = wait.until(
    ExpectedConditions.elementToBeClickable(
        By.id("submit")
    )
);

gomb.click();

// Vár, amíg az elem láthatóvá nem válik
WebElement uzenet = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("success-msg")
    )
);
```

### Implicit Wait

Globális beállítás, minden elemkeresésre érvényesül.

```java
driver.manage()
      .timeouts()
      .implicitlyWait(Duration.ofSeconds(10));
```

---

## 7. Speciális elemek kezelése

### Dropdown (Legördülő menü) kezelése

```java
import org.openqa.selenium.support.ui.Select;

Select dropdown = new Select(
    driver.findElement(By.id("orszag-valaszto"))
);

dropdown.selectByVisibleText("Magyarország");
dropdown.selectByValue("HU");
dropdown.selectByIndex(1);
```

---

### Alert-ek (Felugró ablakok) kezelése

```java
// Váltás a felugró ablakra
Alert alert = driver.switchTo().alert();

// Szöveg lekérése
String alertText = alert.getText();

// "OK" gomb
alert.accept();

// "Mégse" gomb
alert.dismiss();

// Szöveg beírása
alert.sendKeys("Szöveg");
```

---

### Iframes (Beágyazott oldalak) kezelése

```java
// Váltás az iframe-be index alapján
driver.switchTo().frame(0);

// Váltás az iframe-be ID alapján
driver.switchTo().frame("iframe-id");

// Visszaváltás a főoldalra
driver.switchTo().defaultContent();
```

---

# AssertJ

Olvashatóbb és erősebb assertion API.

```java
    assertThat(driver.getTitle()).isEqualTo("Automation Exercise");
    assertThat(name).isNotNull();
    assertThat(text).contains("Hello");
    assertThat(list).hasSize(3);
    assertThat(number).isGreaterThan(10);
```

# Act

Lokálisan lehet veel tesztelni a workflow-t. Linux-os terminálos telepítés és ellenőrzés:

sudo snap remove act
curl --proto '=https' --tlsv1.2 -sSf https://raw.githubusercontent.com/nektos/act/master/install.sh | sudo sh -s -- -b /usr/local/bin

hash -r
act --version
act push --secret SONAR_TOKEN="a_te_tokened" //Írd át!
act push -v -v --bind //Medium-t válaszd. Sokat kell, majd várni mire mindent letölt.

<!--TODO-->

# Local teszthez és githez puska

Új branch-et hozz létre!

Kijelölés: git add .
Mentés: git commit -m "Fixed: "
Feltöltés: git push

Csekkolás:
git status

Local teszt terminálba: mvn clean test
Local build test terminálba:  act push -v -v --bind