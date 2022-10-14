[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2244/gr2244/-/tree/master/)


<h1>Bygging av prosjektet</h1>

Prosjektet bruker maven til bygging og kjøring. Gjør følgende operasjoner i terminalen (gitt at du starter fra rotnivå i terminalen):

- Steg 1: &emsp; <code>cd cleane </code>
- Steg 2: &emsp; <code>mvn clean install </code>
- Steg 3: &emsp; <code>mvn javafx:run -f fxui/pom.xml </code>


<h1>Innhold</h1>

[Om Appen](cleane/readme.md)

Inne i mappen cleane/core/src vil man finne hovedfilene til prosjektet. Herfra er det delt inn i test og main. I main finnes selve programfilene til prosjektet. I test finnes JUnit testene. I cleane/fxui/src/main finnes filene knyttet til brukergrensesnittet. I java/ui finner man kontrolleren-klassen og app-klassen. I resources/ui finner man selve brukergrensesnittet.

<u><h3> Core </h3></u>

_Core_


- [Clean-E Leaderboard](gr2244/core/src/main/java/core/Leaderboard.java) => Filen inneholder kode hvor brukere blir sorteres i en liste i følge poengene sine.

- [Clean-E Task](gr2244/core/src/main/java/core/Task.java) => Filen inneholder koden for task klassen.

- [Clean-E User](gr2244/core/src/main/java/core/User.java) => Filen inneholder koden for user klassen.

_Persistens_

- [JSON](gr2244/core/src/main/java/json) => Håndtering av hvordan fillagring håndteres med serialiserere og deserialiserer samt en Moddule-fil finnes i JSON-mappen, ingen lokalt.

<u><h3>FXUI</h3></u>

_UI_

- [Clean-E FXUI](gr2244/fxui/src/main/resources/ui/cleanE.fxml) => Filen inneholder hovedsiden GUI til alle : AddTask, Kalenderen, Fillagring /Loading & Leaderboard.

_App & Kontroller_

- [Clean-E App](gr2244/fxui/src/main/java/ui/CleanEApp.java) => Filen inneholder main kode for å kjøre appen.

- [Clean-E Controller](gr2244/fxui/src/main/java/ui/CleanEController.java) => Filen inneholder kontrolleren.


