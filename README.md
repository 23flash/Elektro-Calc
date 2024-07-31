## Dieses Projekt ist im Rahmen einer Uni Hausarbeit entstanden
# Voraussetzung
- Java Version 22 (WICHTIG das Pogramm funktioniert NICHT mit älteren Versionen)
- Intellij Idea 
- Internet Verbindung (Einige Dependencys werden durch Maven aus dem Netz gezogen)
- am besten Linux basiertes Betriebssystem aber auch Windows
# Instalation
https://www.jetbrains.com/idea/download/?section=windows
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
  "Name der Fomel": {
  "equation": "i == (q / t)",
  "definition": "Definition der Formel"}
  ```
- formeln müssen statt = ein == angeben
- zudem müssen die Formel zeichen in Lowercase geschriebene sein da die Symja Biblothek die ich zum umstellen und berechne Benötige einige reservierte Zeichen hat und ich diese nicht alle Filtern kann damit es Einheitlich ist habe ich mich dazu entschieden alle Formeln Lower case zu haben.
- man kann pi sqrt tan cos arctan etc verwenden
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