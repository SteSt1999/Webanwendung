1.	Installieren von MySQL Installer (https://dev.mysql.com/downloads/windows/installer/8.0.html)
2.	Im MySQL Installer MySQL Server, MySQL Workbench und Connect/J installieren
3.	Importieren des Projekts
4.	Project Structure --> Libraries --> New --> Java --> mysql-connector-java-8.0.12.jar wählen (standardmäßig  C:\Program Files (x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.12.jar)
5.	Neue Run Configuration erstellen
- New Configuration --> Tomcat Server --> Local
- Unten auf Fix klicken
- New --> Web Application: Exploded --> From Modules…  
6.  Project Structure --> Modules --> Dependencies --> New --> Library... --> Tomcat auswählen
- Falls Tomcat dort nicht auswählbar ist:
  - Project Structure --> Modules --> Dependencies --> New --> Library... --> Java
  - In dem geöffneten Fenster aus dem lib-Ordner von Tomcat "servlet-api.jar" auswählen (standardmäßig C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib)
  - Anschließend auf Plus klicken und im selben Ordner "jsp-api.jar" auswählen
  - Bestätigen und die gerade hinzugefügt Library beim scope von "Compile" auf "Provided" umstellen
7.	Öffne MySQL Workbench
  - Local instance anklicken
  - Neue Schemas anlegen: "banken", "hostbank", "mukabank"
  - Data Import/Restore
  - Bei „Import from Dump Project Folder“ „Projekt Ordner“\Webanwendung\Datenbank“ wählen
  - Start Import
8. Anlegen der Anmeldedaten für die Datenbank
  - Im bin-Ordner von Tomcat (bspw. "C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin") den Ordner "Webanwendung" anlegen
  - In diesem Ordner eine .txt-Datei anlegen mit dem Namen "DBDaten.txt"
  - In die erste Zeile den Benutzernamen und in die zweite Zeile das Passwort eintragen, welche man bei der Installation von MySQL angegeben hat
9. Die Webanwendung sollte funktionieren


Verwendete Versionen:
- JDK 11.0.1
- IntelliJ 2018.2.5 (Ultimate Edition)
- Tomcat 9.0.12
- MySQL Server 8.0.12
- MySQL Workbench 8.0.12
- Connector/J 8.0.12
