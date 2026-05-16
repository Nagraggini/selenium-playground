
[Programozásról szóló könyvem](https://nagraggini.github.io/my-awesome-book/)

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

Terminálba -> git init

VS Code kiegészőt töltsd le: Extension Pack for Java

Ctrl+Shift+P -> Írd be: Java: Create Java Project. -> Válaszd a Maven opciót, majd az maven-archetype-quickstart-ot. -> Nevezd el a projektet. Majd nyomj pár entert a terminálba. 

A pom.xml-ben a dependencies részre ezt másold be:
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.18.0</version>
</dependency>
```

Valamint az 1.7-et írd át 21-re.
```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

# Első tesz

A test/java/../.. mappában lévő java fájlban láthatod az első tesztet.

```java
@Test
    public void ElsoTeszt() {
        // A böngésző indítása
        WebDriver driver = new ChromeDriver();

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