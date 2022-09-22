[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2244/gr2244/-/tree/master/)


<h1>Bygging av prosjektet</h1>

Prosjektet bruker maven til bygging og kjøring.

For å bygge, kjør "mvn install".
Prosjektet må kjøres fra den innerste gr2244 mappen (gr2244/gr244). Derfra må man kjøre "mvn javafx:run". 

<h1>Innhold</h1>

[Om Appen](gr2244/docs/readme.md)

Inne i mappen gr2244/src vil man finne kodeprosjektet. Herfra er det delt inn i test og main. I gr2244/src/main/cleane finnes hovedfilene til prosjektet. I gr2244/src/main/resources finnes brukergrensesnittet. I gr2244/src/test/java/cleane finnes JUnit testene. 

_Applikasjonfiler_

- [Clean-E App](gr2244/src/main/java/cleane/CleanEApp.java) => Filen inneholder main kode for å kjøre appen.

- [Clean-E Controller](gr2244/src/main/java/cleane/CleanEController.java) => Filen inneholder kontrolleren.

- [Clean-E File Management](gr2244/src/main/java/cleane/FileManagement.java) => Filen håndterer skriving fra og lesing til fil.

- [Clean-E Leaderboard](gr2244/src/main/java/cleane/Leaderboard.java) => Filen inneholder kode hvor brukere blir sorteres i en liste i følge poengene sine. (Ikke implementert i innlevering 1)

- [Clean-E Task](gr2244/src/main/java/cleane/Task.java) => Filen inneholder koden for task klassen.

- [Clean-E User](gr2244/src/main/java/cleane/User.java) => Filen inneholder koden for user klassen.

_Brukergrensesnitt_

- [Clean-E App](gr2244/src/main/java/resources/App.fxml) => Filen inneholder GUI til hovedsiden med kalenderen.

- [NewTask](gr2244/src/main/java/resources/newTask.fxml) => Filen inneholder GUI til siden der man lager nye oppgaver.

_Lagringfiler_

- [cleane.txt](gr2244/savestates/cleane.txt) => Filen inneholder den lagrede tilstanden på et txt format.





