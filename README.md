1.	Installieren von MySQL Installer (https://dev.mysql.com/downloads/windows/installer/8.0.html)
2.	Im MySQL Installer MySQL Server, MySQL Workbench und Connect/J installieren
3.	Importieren des Projekts (https://github.com/SteSt1999/Webanwendung.git)
4.	Project Structure --> Libraries --> New --> Java --> mysql-connector-java-8.0.12.jar wählen (standardmäßig  C:\Program Files (x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.12.jar)
5.	Neue Run Configuration erstellen
    a)	New Configuration --> Tomcat Server --> Local
    b)	Unten auf Fix klicken
    c)	New --> Web Application: Exploded --> From Modules…  
6.  Project Structure --> Modules --> Dependencies --> New --> Library... --> Tomcat auswahälen
7.  a)  Öffne MySQL Command Line Client
    b)  Passwort von der Installation eingeben
    c)  Eingeben: „CREATE DATABASE *;“ * = hostbank, mukabank, banken
8.	MySQL Workbench
    a)	Local instance anklicken
    b)	Data Import/Restore
    c)	Bei „Import from Dump Project Folder“ „Projekt Ordner“\Webanwendung\Datenbank“ wählen
    d)	Start Import
9.  a) Im bin-Ordner von Tomcat (bspw. "C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin") den Ordner "Webanwendung" anlegen
    b) In diesem Ordner eine .txt-Datei anlegen mit dem Namen "DBDaten.txt"
    c) In die erste Zeile den Benutzernamen und in die zweite Zeile das Passwort eintragen, welches man bei der Installation von MySQL angegeben hat
10. Die Webanwendung sollte funktionieren
