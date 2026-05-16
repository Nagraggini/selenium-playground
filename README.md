
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

http://www.uitestingplayground.com/
https://the-internet.herokuapp.com/

