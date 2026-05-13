
[Programozásról szóló könyvem](https://nagraggini.github.io/my-awesome-book/)

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


http://www.uitestingplayground.com/
https://the-internet.herokuapp.com/

