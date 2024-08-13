## Dieses Projekt ist im Rahmen einer Uni Hausarbeit entstanden

# Voraussetzung
- Java Version 22 (WICHTIG das Pogramm funktioniert NICHT mit älteren Versionen)
- Intellij Idea 
- Internet Verbindung (Einige Dependencys werden durch Maven aus dem Netz gezogen)
- am besten Linux basiertes Betriebssystem aber auch Windows
# Instalation
- 
- auf https://www.jetbrains.com/idea/download/?section=windows und Intellij Idea ultimate oder community herunterladen
- die Zip entpacken und in Intellij als projekt ordner auswählen
- intellij ist in der lage die Korrekte java installation automatisch zu installieren
- zu Main.java in intellij navigieren und Umschalt + F10 drücken / das grüne Play symbol drücken 


# Ausführen der Applikation

# Aufbau der Applikation
## GUI
Das Folgende Bild ist das Handgezeichnete Icon der Applikation damit es schnell im "icon tray" zu Finden
![cat.png](src%2Fmain%2Fresources%2Fcom%2Fexample%2Felektrocalc%2Fcat.png)

## Handhabung
- die 
## Formeln oder Einheiten hinzufügen
### alle Formeln sowie deren definitionen werden in der equations.json gespeichert
  ```json
  "Test": {
"equation": "zc== -j * 1/((2*Pi*f)*c*c)  ",
"definition": "Test",
"image": "cat.png"
}
  ```
- formeln müssen statt = ein == angeben
- zudem müssen die Formel zeichen in Lowercase geschriebene sein da die Symja Biblothek die ich zum umstellen und berechne Benötige einige reservierte Zeichen hat und ich diese nicht alle Filtern kann damit es Einheitlich ist habe ich mich dazu entschieden alle Formeln Lower case zu haben.
- man kann pi sqrt tan cos arctan etc verwenden
- definitionen müssen ein langer durchgehnder String sein
- das bild muss in com.example.elektrocalc liegen und der image name mit Dateinendung vollständig und richtig angegeben sein
### alle Einheiten und deren Zugehörigkeit in units.json
  ```json
  "i": {
    "unit": "Amperes (A)"
  },
  "q": {
    "unit": "Coulombs (C)"
  },
  ```
 - die Keys müssen Legedlich mit denen der Formel übereinstimmen  
- es muss sonst nichts beachted werden
# Abhängigkeiten der Applikation
- sind in der poml.xml aufgelisted 
- hier eine kurze Zusammenfassung:
  - org.json (json) um Json files zu verarbeiten und zu Nutzen
  - org.matheclipse (Symja) zum Interpretieren der Formeln und umstellen
  - io.github.mkpaz (atlantafx-base) für fertige themes der app
  - org.openjfx (java fx) für alle möglichen GUI elemente
# Grobeinschätzung der Eigenden Arbeits Zeit
- Zeichnen ca. 13 Stunden 
- Dokumentaion ca. 20 Stunden
- Programmieren ca. 100 Stunden 
- Rechereche ( GUI Pogrammierung ,Libarys, welche Pogrammiersprache) ca. 12 Stunden