<h1> Innlevering 2</h1>

I denne iterasjonen har vi utvidet funksjonaliteten i CleanE. Tidligere kunne man bare opprette oppgaver, men nå kan man også utføre oppgaver. I tillegg har vi implementert funksjonalitet for klassen Leaderboard.

I forrige iterasjon hadde vi to forskjellige FXML filer. Vi har valgt å slå sammen disse til én, cleanE.fxml, slik at allt vises i samme scene. Dette er for å gjøre programmet mindre utsatt for feil.

Når en bruker tildeles en oppgave, blir brukeren lagt til i listen users i leaderboard. Når oppgaven blir utført, oppdateres listen med brukere, og vises  i GUI. Når man så trykker på update-knappen i GUI, blir poengtavlen oppdatert og sortert i synkende rekkefølge.

Fillagring er nå implementert med JSON. Vi har ikke lenger en FileManagement klasse fordi vi kaller på CleanEModule direkte i kontrolleren. I neste release vil det mest sannsynlig være naturlig å kapsle inn bruken av jackson og dermed ende opp med en ny FileManagement klasse.

Når det kommer til selve serialiseringen og deserialiseringen, har vi valgt å bruke de standard serialisererne for Task.java og User.java. Dette er gjort fordi Task og User refererer til hverandre noe som skapte problemer, men som lett lot seg løse med å plassere denne koden over User.java klassen: 
</br>
</br>
<code>@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id") </code> 
</br>
</br>
Dette gjør at User får en ID og at denne IDen refereres til i User sin assignedUser. Dermed unngå et evig kall frem og tilbake. JSON filen vil derfor bli på følgende format, når man lagrer Leaderboard som er hierarkiske øverste klassen:
</br>
</br>
<code>
{"users":[{"id": 1,"points": 0,"name":"some name","tasks":
[{"taskName":"some task name","pointsValue":5,
"completed":false,"dueDay":"some weekday",
"assignedUser":1}]},]}</code>
</br>
</br>
Legg merke til at id == assignedUser, fordi assignedUser refererer til den User-instansen med den spesifikke IDen. Som man ser fra JSON filen vil Leaderboard ha en liste med Users, og hver User ha en liste med Tasks, hver task referer tilbake til sin assignedUser.
</br>
</br>
<u><h1>Viktig info!</u></h1>
Grunnen til at vi leverer sent er på grunn av manglende teknisk hjelp. Noen dager satt vi å ventet på hjelp i mer enn 5 timer, mens vi selv forsøkte å løse problemet selv. Når vi endelig løste problemet var det som regel en enkel feil som vi hadde oversett. Dette gjorde at vi brukte mer tid enn vi egentlig trengte.
