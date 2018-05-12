# Contributing
Wenn Sie an diesem Repository mitarbeiten wollen, duscutieren sie bitte erst die änderungen die Sie wünschen über einen `issue` oder via E-Mail, befor sie änderungen vornehmen.

Bitte beachten Sie das wir ein `code of conduct` haben, bitte halten Sie sich an an dies.

## Pull Request Process

1. Stellen Sie sicher das alle Instalation und Build Abhängikeiten entfernt sind 
2. Aktuallisieren Sie die README.md ggf. mit sinnvollen erweiterungen
3. Schreiben Sie Softwaretests für die neuen Komponenten nach den unten definierten Anforderungen
4. Ihr PullRequest wird akzeptiert wenn 2 entwickler ihren ändereungen zustimmen.
<!-- 3. Aktualisiere die Versionsnummer in allen Datein.  -->

## Code of Conduct

### Our Pledge

Um eine offene und einladende Umgebung zu fördern, haben wir als
Mitwirkende und Betreuer uns, verpflichten sich die Teilnehmer an unserem Projekt, eine belästigungsfreie und angenehme Athmosphäre für alle sicherzustellen, unabhängig von Alter und Körper
Größe, Behinderung, ethnische Zugehörigkeit, Geschlechtsidentität und -ausdruck, Erfahrungsniveau, Nationalität, persönliches Aussehen, Rasse, Religion oder sexuelle Identität und
Orientierung.

### Our Standards

Beispiele für Verhaltensweisen die ein positiven eindruck schaffen:

* Freundliche und verständliche Sprache
* Respektieren unterschiedlicher Erfahrungsstaände 
* Konstruktive Kretik akzeptieren
* Empathie gegenüber anderen Community-Mitgliedern zeigen

Beispiele für inaczeptables Verhalten 

* Verwendung sexistischer Sprache und/oder Bilder 
* beleidigende / abfällige Kommentare
* Öffentliche oder private Belästigung
* Veröffentlichen von privaten Informationen anderer Personen
* Andere Verhaltensweisen, die vernünftigerweise als unangemessen angesehen werden könnten 

### Scope of Testing

Um ein erfolgreichen einen PullRequest zu stellen müssen folgende Punkte Beachtet werden. 
* [J-Unit](https://junit.org/junit4/) Tests (in der version 4) für die Neuen Komponenten nach dem Verfahren der Grenzwertanalyse müssen vorhanden [about Grenzwertanalyse](https://de.wikipedia.org/wiki/Dynamisches_Software-Testverfahren#Grenzwertanalyse) 
* Für neu implementierte Userstorys muss ein UI Test in [Selenium](https://www.seleniumhq.org/) geschrieben werden. 
* Es sollte immer der Normalablauf und alle im Lastenheft aufgeschriebenen Atlernativabläufe getestet werden.
* alle Tests müssen auf einem HeadlessCI über [Maven](https://maven.apache.org/) ausführbar sein!  
