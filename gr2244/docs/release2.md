<h1> Innlevering 2</h1>

I denne iterasjonen har vi utvidet funksjonaliteten i CleanE. Tidligere kunne man bare opprette oppgaver, men nå kan man også utføre oppgaver. I tillegg har vi implementert funksjonalitet for klassen Leaderboard.

I forrige iterasjon hadde vi to forskjellige FXML filer. Vi har valgt å slå sammen disse til én, cleanE.fxml, slik at allt vises i samme scene.

Når en bruker tildeles en oppgave, blir brukeren lagt til i listen users i leaderboard. Når oppgaven blir utført, oppdateres listen med brukere, og vises  i GUI. Når man så trykker på update-knappen i GUI, blir poengtavlen oppdatert og sortert i synkende rekkefølge.

Filemanagement klassen er forandret fra å benytte lokal fillagring til JSON.

 <!--
 TODO: Skrive mer om JSON, serlializing...
 >
