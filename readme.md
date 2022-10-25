[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2244/gr2244/-/tree/master/)


<h1>Bygging av prosjektet</h1>

Prosjektet bruker maven til bygging og kjøring. Gjør følgende operasjoner i terminalen (gitt at du starter fra rotnivå i terminalen):

- Steg 1: &emsp; <code>cd CleanE </code>
- Steg 2: &emsp; <code>mvn clean install </code>
- Steg 3: &emsp; <code>mvn javafx:run -f fxui/pom.xml </code>


<h1>Innhold</h1>

[Om Appen](cleane/readme.md)

Inne i mappen cleane/core/src vil man finne hovedfilene til prosjektet. Herfra er det delt inn i test og main. I main finnes selve programfilene til prosjektet. I test finnes JUnit testene. I cleane/fxui/src/main finnes filene knyttet til brukergrensesnittet. I java/ui finner man kontrolleren-klassen og app-klassen. I resources/ui finner man selve brukergrensesnittet.

<u><h3> Core </h3></u>

_Core_


- [Clean-E Leaderboard](CleanE/core/src/main/java/core/Leaderboard.java) => Filen inneholder kode hvor brukere blir sorteres i en liste i følge poengene sine.

- [Clean-E Task](CleanE/core/src/main/java/core/Task.java) => Filen inneholder koden for task klassen.

- [Clean-E User](CleanE/core/src/main/java/core/User.java) => Filen inneholder koden for user klassen.

_Persistens_

- [JSON](CleanE/core/src/main/java/json) => Håndtering av hvordan fillagring håndteres med serialiserere og deserialiserer samt en Moddule-fil finnes i JSON-mappen, ingen lokalt.

<u><h3>FXUI</h3></u>

_UI_

- [Clean-E FXUI](CleanE/fxui/src/main/resources/ui/cleanE.fxml) => Filen inneholder hovedsiden GUI til alle : AddTask, Kalenderen, Fillagring /Loading & Leaderboard.

_App & Kontroller_

- [Clean-E App](CleanE/fxui/src/main/java/ui/CleanEApp.java) => Filen inneholder main kode for å kjøre appen.

- [Clean-E Controller](CleanE/fxui/src/main/java/ui/CleanEController.java) => Filen inneholder kontrolleren.

</br>
<h1>Testdekningsgrad</h1>
Ved å kjøre kommandoen <code>mvn test</code> opprettes en testdekningsrapport, som heter <b>index.html</b>.
Denne finner man ved å gå inn i mappen CleanE/core/target/site/jacoco. 
Når HTML-filen åpnes i nettleseren, så får man frem prosjektets testdekningsgrad.

</br>
<h1> Arbeidsvaner og arbeidsflyt </h1>

I denne iterasjonen av prosjektet har vi prøvd å bli enda bedre på å bruke gitlab som et verktøy i utviklingsprosessen vår. Vi har laget utviklingsoppgaver (issues) i gitlab som beskriver alt vi har gjort. For hver utviklingsoppgave har vi branchet slik at det man arbeider med ikke skaper problemer med det noen andre arbeider med. Vi har også vært flinke på å nevne issue nummer i commit-meldingene slik at man enkelt kan se hvilke commits som relaterer til hvilke issues når alle branchene har blitt satt sammen i master. Vi har også benyttet mye parprogrammering i denne iterasjonen. Dette kan man se i commit-meldingene der vi har skrevet "Medforfattet av: @brukernavn". Vi skjønte ikke helt i starten at det lønte seg med @brukernavn i steden for å bare skrive navn, men det lærte vi raskt.

<h1> Dokument-metafor </h1>

I dette prosjektet velger vi dokument-metafor i stedet for implisitt lagring, fordi vi tror at appen passer best med bare en liste for å lagre informasjon av brukere. Det vil si at brukerne velger selv hvis de vil overskrive listen som er allerede lagret for å begynne på nytt.

Appen lagrer brukeres informasjon som står på listen hver ganger en trykker på SAVE knappen, og laster ned informasjonen fra filen inn til listen med LOAD knappen. 

<<<<<<< HEAD
Med andre ord finnes det ikke en ny liste, fordi vi bruker bare en liste for en hele uke. Her antar vi at listen skal bli oppdatert hver uke, derfor lister appen bare 7 dager i stedet for en måned med 30 dager. Vi antar også at husholdet er en gruppe som bor sammen under en lenger tid. Dermed er Scoreboard konkurransen gyldig i 7 dager, før den begynner på nytt.
=======
Med andre ord, det finnes ikke en ny liste fordi vi bruker på en måte bare en liste for en hele uke. Her antar vi at listen skal bli oppdatert hver uke, derfor appen lister bare 7 dager i stedet for en måned med 30 dager. Forresten, vi antar også at at husholdere er den sammen gruppen som bo sammen på en ganske lang stund. Dermed den Scoreboard konkurransen er gyldig og skal begynne på nytt etter 7 dager.

<p style="text-align:center;"><img src="../docs/prosjekt-images/Clean-E_SAVE_LOAD.png"  width="300" height="100" ></p>

<p style="text-align:center;">
<small><em >Save/Load</em></small></p>
>>>>>>> d4806ba2bf338d17b6a143d7b7543e51db5253ac
